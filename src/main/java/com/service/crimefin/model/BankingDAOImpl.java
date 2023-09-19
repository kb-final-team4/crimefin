package com.service.crimefin.model;

import com.service.crimefin.domain.BankingVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<BankingVO> getBanking(BankingVO bankingVO) {
        return sqlSession.selectList(NS+"getBanking", bankingVO);
    }
    @Override
    public List<BankingVO> getBankingByAccountId(BankingVO bankingVO) {
        return sqlSession.selectList(NS+"getBankingByAccountId", bankingVO);
    }

    @Override
    public List<BankingVO> getBankingByMemberId(BankingVO bankingVO) {
        return sqlSession.selectList(NS+"getBankingByMemberId", bankingVO);
    }
    @Override
    public int updateBanking(BankingVO bankingVO) {
        return sqlSession.update(NS+"updateBanking", bankingVO);
    }

    @Override
    public int deleteBanking(BankingVO bankingVO) {
        return sqlSession.delete(NS+"deleteBanking",bankingVO );
    }


}
