package com.service.crimefin.model;

import com.service.crimefin.domain.MemberVO;

import java.util.List;

public interface MemberDAO {
    //0. test 메서드
    public List<MemberVO> select();

    public int insertMember(MemberVO memberVO);
    public int deleteMember(MemberVO memberVO);
    public int updateMemberInfo(MemberVO memberVO);
    public int updateMemberPassword(MemberVO memberVO);
    public MemberVO login(MemberVO memberVO);
    public String findMemberId(MemberVO memberVO);
    public int isExistMemberId(String memberId);
    public int isExistMemberEmail(String email);
    public int isExistMemberPhone(String phone);

}
