package com.service.crimefin.controller;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@RestController
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/asset/auth")
    public ResponseEntity addAccount(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception {
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

            Random random = new Random(); //랜덤 함수 선언
            int createNum = 0;            //1자리 난수
            String ranNum = "";           //1자리 난수 형변환 변수
            int letter = 4;               //난수 자릿수: 4
            String resultNum = "";        //결과 난수

            for (int i = 0; i < letter; i++) {
                createNum = random.nextInt(9);   //0부터 9까지 올 수 있는 1자리 난수 생성
                ranNum = Integer.toString(createNum);  //1자리 난수를 String으로 형변환
                resultNum += ranNum;                   //생성된 난수(문자열)을 원하는 수(letter)만큼 더하며 나열
            }

            //거래내역 테이블 생성
            BankingVO bankingVO = new BankingVO();
            bankingVO.setAccountNum(accountNum);
            bankingVO.setMemberId(memberId);
            //bankingVO.setBankingDate();
            bankingVO.setDeposit(1); //1원 송금
            bankingVO.setDepositName(resultNum); //입금자명 입력
            //bankingVO.setBalance();

            int result2 = assetService.insertBanking(bankingVO);

            if(result2 != 0) { //거래내역 생성 성공
                session= request.getSession(true); //세션 있으면 기존 세션 반환, 없으면 세션 새로 만듬
                session.setAttribute("authNum", resultNum);
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

    @PostMapping("/asset/auth/confirm")
    public ResponseEntity checkAccount(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(false); //세션이 있으면 기존 세션 반환, 세션 없으면 null 반환

        //session에서 authNum을 가져옴
        String authNum = (String) session.getAttribute("authNum");

        String inputNum = (String) requestJsonHashMap.get("inputNum");

        if(inputNum.equals(authNum)) {
            return new ResponseEntity(1, HttpStatus.OK);
        } else {
            return new ResponseEntity(0, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/asset/dashboard")
    public ResponseEntity getAccounts(HttpServletRequest request) throws Exception{
        System.out.println("대시보드 계좌 조회 요청 들어옴");
        HttpSession session = request.getSession(false); //세션이 있으면 기존 세션 반환, 세션 없으면 null 반환
        MemberVO memberVO = (MemberVO) session.getAttribute("userInfo");
        String memberId = memberVO.getMemberId();

        List<AccountVO> rvo = assetService.getAccounts(memberId);

        for(AccountVO a : rvo) System.out.println(a); //확인용

        if (rvo != null) { //계좌 있으면
            return new ResponseEntity(rvo, HttpStatus.OK);
        } else { //계좌 없으면
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/asset/dashboard/time")
    public ResponseEntity getBanking(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception{
        HashMap map = new HashMap();
        String accountNum = (String) requestJsonHashMap.get("accountNum");
        String startdate = (String) requestJsonHashMap.get("startdate");
        String enddate = (String) requestJsonHashMap.get("enddate");

        map.put("accountNum",accountNum);
        map.put("startDate",startdate);
        map.put("endDate",enddate);

        List<BankingVO> rvo = assetService.getBanking(map);

        if (rvo != null) { //거래 내역 있으면
            return new ResponseEntity(rvo, HttpStatus.OK);
        } else { //거래 내역 없으면
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
