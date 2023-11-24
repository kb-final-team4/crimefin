package com.service.crimefin.controller;

import com.service.crimefin.domain.ChecklistVO;
import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.service.PhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@Controller
public class PhishingController {

    @Autowired
    private PhishingService phishingService;

    //번호조회, 링크조회 이후 history에 저장하는 부분 추가 예정
    
    @GetMapping(value = "/phishing", params = {"phoneNum"})
    public ResponseEntity checkPhoneNum(@RequestParam String phoneNum) throws Exception{

        PhishingInfoVO rvo = phishingService.isPhishingNumber(phoneNum);

        if (rvo != null) { //번호 있으면
            return new ResponseEntity(rvo, HttpStatus.OK);
        } else { //번호 없으면
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        
    }

    @PostMapping("/phishing/link")
    public ResponseEntity checkLinkIsSpam(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception {

        String url = (String) requestJsonHashMap.get("inputUrl");
        System.out.println(url);

        int result = phishingService.isPhishingLink(url);
        System.out.println(result);

        return new ResponseEntity(result, HttpStatus.OK);

    }

    @PostMapping("/phishing/check")
    public ResponseEntity saveChecklist(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception{
        System.out.println("BE : saveCheckList mem id : "+ requestJsonHashMap.get("memberId"));
        System.out.println("BE : saveCheckList idx : " + requestJsonHashMap.get("idx"));

        ChecklistVO pvo = new ChecklistVO();
        pvo.setMemberId((String) requestJsonHashMap.get("memberId"));
        pvo.setIdx((int) requestJsonHashMap.get("idx"));

        System.out.println();

        int rst = phishingService.insertChecklist(pvo);
        if(rst > 0)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // AI로 맥락 조회
    @PostMapping( "/phishing/context")
    public ResponseEntity checkContext(@RequestBody List<HashMap<String, Object>> requestJsonArray) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<HashMap<String, Object>>> request = new HttpEntity<>(requestJsonArray, headers);

        RestTemplate restTemplate = new RestTemplate();

        // FastAPI 서버 URL과 엔드포인트로 변경되어야 합니다.
        String fastApiUrl = "http://localhost:8000/predict";
        System.out.println("fast api 서버와 연결 성공");

        // POST 요청을 보냅니다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(fastApiUrl, HttpMethod.POST, request, String.class);
        System.out.println("fast api 서버에 post 요청 성공");
        
        System.out.println(responseEntity.getBody());

        return responseEntity;

    }
}
