package com.service.crimefin.service;

import com.service.crimefin.domain.ChecklistVO;
import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.model.ChecklistDAO;
import com.service.crimefin.model.PhishingInfoDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

@Service
public class PhishingServiceImpl implements PhishingService {

    @Autowired
    private PhishingInfoDAO phishingInfoDAO;

    @Autowired
    private ChecklistDAO checklistDAO;

    @Override
    public PhishingInfoVO isPhishingNumber(String phoneNum) {
        return phishingInfoDAO.isPhishingNumber(phoneNum);
    }

    @Override
    public int isPhishingLink(String url) throws Exception {

        String encodedURl = Base64.getEncoder().withoutPadding().encodeToString(url.getBytes());
        //System.out.println("encoded string : "+ encodedURl);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/urls/"+encodedURl))
                .header("accept", "application/json")
                .header("x-apikey", "2d51d2d86c432e6826f2f8110b9b932042791e63c6a85a627ff9a90f057e4e28")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

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
            return 2;
        }
        JSONObject vtResultLastAnalysisResults = null;

        try {
            vtResultLastAnalysisResults = (JSONObject) vtResultAttributes.get("last_analysis_results");
        }catch (Exception e) {
            //백신들이 탐지 못하는 링크임. 없어진 링크나 잘못된 입력임
            //다음 페이지로 가는 path 리턴하기
            System.out.println("This link is unavailable, let's try our AI analysis.");
            return 2;
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
            return 1;
        }

        else if(maliciousCount + suspiciousCount == 0 && harmlessCount > 0) {
            System.out.println("This link is Safe.");
            return 3;
        }
        else {
            System.out.println("This link is undetected. But you can try out our AI analysis for this link.");
            return 2;
        }

    }

    @Override
    public int insertChecklist(ChecklistVO checklistVO) throws SQLException {
        return checklistDAO.insertChecklist(checklistVO);
    }
}
