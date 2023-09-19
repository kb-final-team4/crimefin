package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankingDAOImpl implements BankingDAO {
    @Autowired
    private SqlSession sqlSession;

    public static final String NS="sql.banking.mapper.";

    @Override
    public int insertBanking(BankingVO bankingVO) {
        return sqlSession.insert(NS+"insertBanking", bankingVO);
    }
}
