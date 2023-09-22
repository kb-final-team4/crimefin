package com.service.crimefin.controller;

import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.dto.MessageDTO;
import com.service.crimefin.dto.SendNumResponseDTO;
import com.service.crimefin.dto.SmsResponseDTO;
import com.service.crimefin.service.SmsService;
import com.service.crimefin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@RestController
public class UserController {

    private static final String prefixMessage = "Crime-Fin 서비스\n인증번호 : ";

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception{
        String memberId = (String) requestJsonHashMap.get("memberId");
        String password = (String) requestJsonHashMap.get("password");
        System.out.println("id : "+ memberId + " pw : "+ password);

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(memberId);
        memberVO.setPassword(password);
        MemberVO rvo = userService.login(memberVO);

        if (rvo != null) { //로그인 성공
            HttpSession session = request.getSession(true);
            session.setAttribute("userInfo", rvo);
            return new ResponseEntity(rvo, HttpStatus.OK);
        } else { //로그인 실패
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/user", params = {"name", "phone"})
    public ResponseEntity findUserId(@RequestParam String name, @RequestParam String phone) throws Exception{

        MemberVO member = new MemberVO();
        member.setName(name);
        member.setPhone(phone);
        String memberId = userService.findMemberId(member);

        if(memberId != null) {
            return new ResponseEntity(memberId, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/user")
    public ResponseEntity registerUser(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpSession session) throws Exception{
        MemberVO vo = new MemberVO();
        vo.setMemberId((String) requestJsonHashMap.get("memberId"));
        vo.setEmail((String) requestJsonHashMap.get("email"));
        vo.setPassword((String) requestJsonHashMap.get("password"));
        vo.setName((String) requestJsonHashMap.get("username"));
        vo.setPhone((String) requestJsonHashMap.get("phone"));
        vo.setAddress((String) requestJsonHashMap.get("address"));
        vo.setSex(Integer.parseInt((String)requestJsonHashMap.get("sex")));
        vo.setMessageOk(Integer.parseInt((String)requestJsonHashMap.get("messageOk")));

        System.out.println(vo);
        //DB insert
        int result = userService.insertMember(vo);

        if(result != 0) {
            return new ResponseEntity(vo.getMemberId(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/user")
    public ResponseEntity updateUserInfo(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception{

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

    @PutMapping("/user/pwd")
    public ResponseEntity updateUserPassword(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception {
        System.out.println("비밀번호 변경 요청 들어옴");
        String memberId = (String) requestJsonHashMap.get("memberId");
        String newPw = (String) requestJsonHashMap.get("newPassword");
        String newPwConfirm = (String) requestJsonHashMap.get("confirmPassword");

        System.out.println("아이디 = "+memberId);
        System.out.println("newPw = "+newPw);
        System.out.println("nowPwConfirm = "+newPwConfirm);

        boolean flag = true;
        if(newPw.equals(newPwConfirm)) { //password 재설정해도 되는 상황

            MemberVO memberVO = new MemberVO();
            memberVO.setMemberId(memberId);
            memberVO.setPassword(newPw);

            //DB update
            int result = userService.updateMemberPassword(memberVO);
            if(result != 0) {
                return new ResponseEntity(memberId, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        else { //새 비밀번호랑 새 비밀번호 확인이랑 안 맞음
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/user/withdrawal")
    public ResponseEntity deleteUser(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession(false); //세션이 있으면 기존 세션 반환, 세션 없으면 null 반환
        MemberVO pvo = (MemberVO) session.getAttribute("userInfo");
        pvo.setPassword((String) requestJsonHashMap.get("password")); //사용자가 입력창에 입력한 패스워드

        //DB delete
        int result = userService.deleteMember(pvo);
        if(result != 0) {
            return new ResponseEntity(pvo.getMemberId(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/user/duplicate", params={"memberId"})
    public ResponseEntity isExistUserId(@RequestParam String memberId) throws Exception{
        System.out.println(memberId);

        int result = userService.isExistMemberId(memberId);

        if(result == 0) { //중복되는 경우
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else //사용가능한 아이디인 경우
        {
            return new ResponseEntity(memberId, HttpStatus.OK);
        }
    }

    @PostMapping("/user/sendNum")
    public ResponseEntity sendNum(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpServletRequest req) throws Exception {
        System.out.println("인증번호 요청 들어옴");
        //json 객체로 넘어온 값 phoneNum을 저장
        String phone = (String) requestJsonHashMap.get("phone");
        System.out.println("phone = " + phone);

        MessageDTO messageDTO = new MessageDTO();

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

        messageDTO.setTo(phone);
        messageDTO.setContent(prefixMessage+resultNum);
        SmsResponseDTO smsResponseDTO = smsService.sendSms(messageDTO);

        //ResponseEntity에 현재시간, 인증번호 저장
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        SendNumResponseDTO dto = new SendNumResponseDTO();
        dto.setResultNum(resultNum);
        dto.setStartTime(startTime);

        HttpSession session= req.getSession(true); //세션 있으면 기존 세션 반환, 없으면 세션 새로 만듬
        session.setAttribute("dto", dto);
        System.out.println("인증 번호 요청 세션 아이디 : " +session.getId());
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(value = "/user/checkNum", params={"authNum"})
    public ResponseEntity checkNum(@RequestParam String authNum, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false); //세션이 있으면 기존 세션 반환, 세션 없으면 null 반환
        System.out.println("인증번호 확인 :" + authNum);
        System.out.println("인증 번호 확인 세션 아이디 : " +session.getId());

        //session에서 authNum과 당시 시각을 가져옴
        SendNumResponseDTO dto = (SendNumResponseDTO) session.getAttribute("dto");
        String num = dto.getResultNum();
        Timestamp startTime = dto.getStartTime();
        Date endTime = new Date();

        //시간이 3분 이내인지 확인
        //인증번호가 일치하는지 확인
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
        System.out.println("startTime.getTime = " + startTime.getTime());
        System.out.println("endTime.getTime" + endTime.getTime());
        long diff = endTime.getTime() - startTime.getTime();
        System.out.println("diff is = " + diff);
        long sec = diff / 1000;
        System.out.println("sec");
        if (sec > 180) {
            System.out.println("time over");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else if (authNum.equals(num)){
            System.out.println("success");
            return new ResponseEntity(authNum, HttpStatus.OK);
        }
        else {
            System.out.println("authNum is not matched");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        
    }

}
