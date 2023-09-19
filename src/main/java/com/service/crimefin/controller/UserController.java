package com.service.crimefin.controller;

import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:9999" }, allowCredentials = "true")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody HashMap<String, Object> requestJsonHashMap, HttpSession session) throws Exception{
        String memberId = (String) requestJsonHashMap.get("memberId");
        String password = (String) requestJsonHashMap.get("password");
        System.out.println("id : "+ memberId + " pw : "+ password);

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(memberId);
        memberVO.setPassword(password);
        MemberVO rvo = userService.login(memberVO);

        if (rvo != null) { //로그인 성공
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
        vo.setName((String) requestJsonHashMap.get("name"));
        vo.setPhone((String) requestJsonHashMap.get("phone"));
        vo.setAddress((String) requestJsonHashMap.get("address"));
        vo.setSex((int) requestJsonHashMap.get("sex"));
        vo.setMessageOk((int) requestJsonHashMap.get("messageOk"));

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
        String nowPw = (String) requestJsonHashMap.get("password");
        String newPw = (String) requestJsonHashMap.get("newPw");
        String newPwConfirm = (String) requestJsonHashMap.get("newPwConfirm");

        //MemberVO pvo = (MemberVO) req.getSession().getAttribute("userInfo");
        //session에서 userInfo 받아왔다고 가정하고 메서드 진행
        //session에 비밀번호 담아두지 않는걸로 확정되면 DB에서 가져오기
        MemberVO pvo = new MemberVO();
        pvo.setMemberId("test0912");
        pvo.setPassword("1234");
        
        boolean flag = true;
        if(pvo.getPassword().equals(nowPw)) {
            if(newPw.equals(newPwConfirm)) { //password 재설정해도 되는 상황

                MemberVO memberVO = new MemberVO();
                memberVO.setMemberId(pvo.getMemberId());
                memberVO.setPassword(newPw);

                //DB update
                int result = userService.updateMemberPassword(memberVO);
                if(result != 0) {
                    return new ResponseEntity(pvo.getMemberId(), HttpStatus.OK);
                }
                else {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
            else { //새 비밀번호랑 새 비밀번호 확인이랑 안 맞음
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        else { //현재 비밀번호랑 로그인 비밀번호 안맞음
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/user/withdrawal")
    public ResponseEntity deleteUser(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception{
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
    }

}
