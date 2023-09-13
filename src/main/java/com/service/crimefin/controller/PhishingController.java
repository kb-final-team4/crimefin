package com.service.crimefin.controller;

import com.service.crimefin.domain.HistoryVO;
import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.service.PhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PhishingController {

    @Autowired
    private PhishingService phishingService;

    @GetMapping(value = "/phishing", params = {"phoneNum"})
    public ResponseEntity checkPhoneNum(@RequestParam String phoneNum) throws Exception{

        PhishingInfoVO rvo = phishingService.isPhishingNumber(phoneNum);

        if (rvo != null) { //번호 있으면
            return new ResponseEntity(rvo, HttpStatus.OK);
        } else { //번호 없으면
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }



    @GetMapping("/phishing/link")
    public void checkLinkIsSpam(HttpSession session, String inputUrl) throws Exception {

        //String memberId = session.getAttribute("memberId");
        //String url = inputUrl;
        String url = "ca01.kro.kr";
        //String url = "naver.com";
        String path = "링크검사하는페이지";

        String encodedURl = Base64.getEncoder().withoutPadding().encodeToString(url.getBytes());
        //System.out.println("encoded string : "+ encodedURl);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/urls/"+encodedURl))
                .header("accept", "application/json")
                .header("x-apikey", "2d51d2d86c432e6826f2f8110b9b932042791e63c6a85a627ff9a90f057e4e28")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());

        String vtResultStr = response.body();

        JSONParser parser = new JSONParser();
        Object vtResultObj = parser.parse(vtResultStr);

        JSONObject vtResult = (JSONObject) vtResultObj;
        JSONObject vtResultData = (JSONObject) vtResult.get("data");

        JSONObject vtResultAttributes = null;
        try {
            vtResultAttributes = (JSONObject) vtResultData.get("attributes");
        } catch (Exception e) {
            //백신들이 탐지 못하는 링크임. 없어진 링크나 잘못된 입력임
            //다음 페이지로 가는 path 리턴하기
            System.out.println("This link is unavailable, let's try our AI analysis.");
            return;
        }
        JSONObject vtResultLastAnalysisResults = null;

        try {
            vtResultLastAnalysisResults = (JSONObject) vtResultAttributes.get("last_analysis_results");
        }catch (Exception e) {
            //백신들이 탐지 못하는 링크임. 없어진 링크나 잘못된 입력임
            //다음 페이지로 가는 path 리턴하기
            System.out.println("This link is unavailable, let's try our AI analysis.");
            return;
        }

        //System.out.println(vtResultSeclookup.get("category"));
        Set vaccineSet = vtResultLastAnalysisResults.keySet();
        int maliciousCount = 0;
        int suspiciousCount = 0;
        int harmlessCount = 0;

        for(Object s : vaccineSet) {
            JSONObject vtResultVaccine = (JSONObject) vtResultLastAnalysisResults.get((String) s);
            if(((String) vtResultVaccine.get("category")).equals("malicious")) {
                maliciousCount++;
            }
            else if(((String) vtResultVaccine.get("category")).equals("suspicious")){
                suspiciousCount++;
            }
            else if(((String) vtResultVaccine.get("category")).equals("harmless")) {
                harmlessCount++;
            }
        }

        if(maliciousCount > 0) {
            System.out.println("This link is SPAM!!!!");
            return;
            //스팸일때 이 링크 저장하는 쿼리문 들어있는 서비스DAO의 적절한 메서드 호출하기
            //HistoryVO historyVO = new HistoryVO(newHistoryId, memberId, null, null, testurl);
            //serviceDAO.saveHistoryWithLink(historyVO);
            //path = "링크스팸이니까결과페이지path";
            //return path;

        }

        else if(maliciousCount + suspiciousCount == 0 && harmlessCount > 0) {
            System.out.println("This link is Safe.");
            return;
            //안전하니 결과페이지로 가는 path 리턴하기
            //path = "링크안전결과페이지path"
            //return path;

        }
        else {
            System.out.println("This link is undetected. But you can try out our AI analysis for this link.");
            return;
            //다음 페이지로 가는 path 리턴하기
            //return path;
        }
    }
}
