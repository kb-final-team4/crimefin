package com.service.crimefin.service;

import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.model.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public MemberVO login(MemberVO memberVO) {
        return memberDAO.login(memberVO);
    }

    @Override
    public String findMemberId(MemberVO memberVO) {
        return memberDAO.findMemberId(memberVO);
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        return memberDAO.insertMember(memberVO);
    }

    @Override
    public int updateMemberInfo(MemberVO memberVO) {
        return memberDAO.updateMemberInfo(memberVO);
    }

    @Override
    public int updateMemberPassword(MemberVO memberVO) {
        return memberDAO.updateMemberPassword(memberVO);
    }

    @Override
    public int deleteMember(MemberVO memberVO) {
        return memberDAO.deleteMember(memberVO);
    }

    @Override
    public int isExistMemberId(String memberId) {
        return memberDAO.isExistMemberId(memberId);
    }
}
