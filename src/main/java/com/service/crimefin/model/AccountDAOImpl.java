package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public int deleteAccount(AccountVO accountVO) {
        return sqlSession.delete(NS+"deleteAccount", accountVO);
    }
}
