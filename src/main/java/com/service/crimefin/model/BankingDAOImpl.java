package com.service.crimefin.model;

import com.service.crimefin.domain.BankingVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BankingDAOImpl implements BankingDAO {

    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.banking.mapper.";

    @Override
    public int insertBanking(BankingVO bankingVO) {
        return sqlSession.insert(NS+"insertBanking", bankingVO);
    }

    @Override
    public List<BankingVO> getBanking(HashMap map) { return sqlSession.selectList(NS + "getBanking", map);
    }

    @Override
    public List<BankingVO> getMaxBalanceBanking(String memberId) {
        return sqlSession.selectList(NS+"getMaxBalanceBanking", memberId);
    }

    @Override
    public List<BankingVO> getLimitBanking() {
        return sqlSession.selectList(NS+"getLimitBanking");
    }

}
