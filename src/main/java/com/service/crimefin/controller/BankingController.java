package com.service.crimefin.controller;

import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.service.BankingService;
import com.service.crimefin.service.UserService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@RestController
public class BankingController {

    @Autowired
    private BankingService bankingService;
    @Autowired
    private SqlSession sqlSession;

    @PostMapping("/asset/banking")
    public ResponseEntity insertBanking(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpSession session) throws Exception{
        BankingVO vo = new BankingVO();

        vo.setBankingId((String)requestJsonHashMap.get("bankingId"));
        vo.setMemberId((String)requestJsonHashMap.get("memberId"));
        vo.setAccountId((String)requestJsonHashMap.get("accountId"));
        vo.setBankingDate((String)requestJsonHashMap.get("bankingDate"));

        // deposit이 null일 경우 기본값 설정
        Integer deposit = (Integer) requestJsonHashMap.get("deposit");
        if (deposit == null) deposit = 0;
        else vo.setDeposit(deposit);

        // depositName이 null일 경우 기본값 설정
        String depositName = (String)requestJsonHashMap.get("depositName");
        if (depositName == null) depositName = " ";
        else vo.setDepositName(depositName);

        // withdrawal이 null일 경우 기본값 설정
        Integer withdrawal = (Integer) requestJsonHashMap.get("withdrawal");
        if (withdrawal == null) withdrawal = 0;
        else vo.setWithdrawal(withdrawal);

        // withdrawalTo이 null일 경우 기본값 설정
        String withdrawalTo = (String)requestJsonHashMap.get("withdrawalTo");
        if (withdrawalTo == null) withdrawalTo = " ";
        else vo.setWithdrawalTo(withdrawalTo);

        int result = bankingService.insertBanking(vo);

        if (result != 0) { // 거래 등록 성공
            System.out.println("거래 등록 성공!");
            return new ResponseEntity(vo, HttpStatus.OK);
        } else { //거래 등록 실패
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/asset/banking")
    public ResponseEntity getBanking() throws Exception{

        List<BankingVO> bankingAll = sqlSession.selectList("getBanking");

        if(bankingAll != null) {
            System.out.println("사용자, 계좌 아이디 구분없이 총 거래내역 조회 성공!");
            return new ResponseEntity(bankingAll, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/asset/banking", params = {"memberId"})
    public ResponseEntity getBankingByMemberId(@RequestParam String memberId) throws Exception{

        List<BankingVO> bankingById = sqlSession.selectList("getBankingByMemberId", memberId);

        if(bankingById != null) {
            System.out.println("사용자별 거래내역 조회 성공!");
            return new ResponseEntity(bankingById, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/asset/banking", params = {"accountId"})
    public ResponseEntity getBankingByAccountId(@RequestParam String accountId) throws Exception{

        List<BankingVO> bankingByAccountId = sqlSession.selectList("getBankingByAccountId", accountId);

        if(bankingByAccountId != null) {
            System.out.println("거래 아이디별 거래내역 조회 성공!");
            return new ResponseEntity(bankingByAccountId, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

  /*  @PutMapping("/asset/banking")
    public ResponseEntity updateBanking(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception{

        MemberVO vo = new MemberVO();
        vo.setMemberId((String) requestJsonHashMap.get("memberId"));
        vo.setEmail((String) requestJsonHashMap.get("email"));
        vo.setName((String) requestJsonHashMap.get("name"));
        vo.setAddress((String) requestJsonHashMap.get("address"));

        //DB update
        int result = userService.updateMemberInfo(vo);

        if(result != 0)
        {
            return new ResponseEntity(vo, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping ("/asset/banking")
    public ResponseEntity deleteBanking(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception{
        //MemberVO pvo = (MemberVO) req.getSession().getAttribute("userInfo");
        //session에서 userInfo 받아왔다고 가정하고 메서드 진행
        MemberVO pvo = new MemberVO();
        pvo.setMemberId("test0913");
        pvo.setPassword((String) requestJsonHashMap.get("password")); //사용자가 입력창에 입력한 패스워드

        //DB delete
        int result = userService.deleteMember(pvo);
        if(result != 0) {
            return new ResponseEntity(pvo.getMemberId(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }*/

}
