package com.service.crimefin.controller;

import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.service.PhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

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

}
