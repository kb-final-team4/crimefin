package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Autowired
    private SqlSession sqlSession;

    public static final String NS="sql.account.mapper.";

    @Override
    public int insertAccount(AccountVO accountVO) {
        return sqlSession.insert(NS+"insertAccount", accountVO);
    }

    @Override
    public List<AccountVO> getAccounts(String memberId) {return sqlSession.selectList(NS+"getAccounts", memberId);
    }

    @Override
    public void deleteBanking(String accountNum) {
        sqlSession.delete(NS+"deleteBanking",accountNum);
    }

    @Override
    public int deleteAccount(String accountNum) {
        //sqlSession.delete(NS+"deleteBanking", accountNum);
        return sqlSession.delete(NS+"deleteAccount", accountNum);
    }

    @Override
    public int updateNickNameAndLimit(AccountVO accountVO) {
        return sqlSession.update(NS+"updateNickNameAndLimit", accountVO);
    }
}
