package com.service.crimefin.service;

import com.service.crimefin.domain.MemberVO;

public interface UserService {

    public MemberVO login(MemberVO memberVO);
    public String findMemberId(MemberVO memberVO);
    public int insertMember(MemberVO memberVO);
    public int updateMemberInfo(MemberVO memberVO);
    public int updateMemberPassword(MemberVO memberVO);
    public int deleteMember(MemberVO memberVO);
}
