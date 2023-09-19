package com.service.crimefin.controller;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@Controller
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping("/asset")
    public ResponseEntity addAccount(@RequestBody HashMap<String, Object> requestJsonHashMap)throws Exception{
        AccountVO accountVO = new AccountVO();

        accountVO.setAccountId((String)requestJsonHashMap.get("accountId"));
        accountVO.setMemberId((String)requestJsonHashMap.get("memberId"));
        accountVO.setAccountNum((String)requestJsonHashMap.get("accountNum"));
        accountVO.setBank((String)requestJsonHashMap.get("bankName"));
        accountVO.setBalance(Integer.parseInt((String)requestJsonHashMap.get("balance")));
        accountVO.setLimit(Integer.parseInt((String)requestJsonHashMap.get("limit")));

        System.out.println(accountVO);

        int result=assetService.insertAccount(accountVO);

        if(result != 0) {
            return new ResponseEntity(accountVO.getMemberId(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/asset/auth")
    public ResponseEntity authAccount(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false); //세션이 있으면 기존 세션 반환, 세션 없으면 null 반환

        //session에서 member id를 가져옴
        MemberVO memberVO = (MemberVO) session.getAttribute("userInfo");
        String memberId = memberVO.getMemberId();

        String accountNum = (String) requestJsonHashMap.get("accountNum");
        String bankName = (String) requestJsonHashMap.get("bankName");

        AccountVO accountVO = new AccountVO();
        accountVO.setMemberId(memberId);
        accountVO.setAccountNum(accountNum);
        accountVO.setBank(bankName);

        int result = assetService.insertAccount(accountVO);

        if(result != 0) { //계좌 임시로 생성 성공

            //거래내역 테이블 생성
            BankingVO bankingVO = new BankingVO();
            bankingVO.setAccountNum(accountNum);
            bankingVO.setMemberId(memberId);
            //bankingVO.setBankingDate();
            bankingVO.setDeposit("1");
            bankingVO.setDepositName("랜덤입금자");
            //bankingVO.setBalance();

            int result2 = assetService.insertBanking(bankingVO);

            if(result2 != 0) { //거래내역 생성 성공
                return new ResponseEntity(HttpStatus.OK);
            }
            else { //거래내역 생성 실패
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }


        }
        else { //계좌 생성 실패
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }


    }
}
