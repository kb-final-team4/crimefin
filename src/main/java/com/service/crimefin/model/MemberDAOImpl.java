package com.service.crimefin.model;

import com.service.crimefin.domain.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.member.mapper.";

    @Override
    public List<MemberVO> select() {
        return sqlSession.selectList(NS+"select");
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        return sqlSession.insert(NS+"insertMember", memberVO);
    }

    @Override
    public int deleteMember(MemberVO memberVO) {
        return sqlSession.delete(NS+"deleteMember", memberVO);
    }

    @Override
    public int updateMemberInfo(MemberVO memberVO) {
        return sqlSession.update(NS+"updateMember", memberVO);
    }

    @Override
    public int updateMemberPassword(MemberVO memberVO) {
        return sqlSession.update(NS + "updateMemberPassword", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne(NS + "selectMember", memberVO);
    }

    @Override
    public String findMemberId(MemberVO memberVO) {
        return sqlSession.selectOne(NS + "selectMemberId", memberVO);
    }

    @Override
    public int isExistMemberId(String memberId) {
        return sqlSession.selectOne(NS + "isExistMemberId", memberId);
    }

    @Override
    public int isExistMemberEmail(String email) {
        return sqlSession.selectOne(NS + "isExistMemberEmail", email);
    }

    @Override
    public int isExistMemberPhone(String phone) {
        return sqlSession.selectOne(NS + "isExistMemberPhone", phone);
    }
}
