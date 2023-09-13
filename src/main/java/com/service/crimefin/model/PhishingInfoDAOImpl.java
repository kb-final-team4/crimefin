package com.service.crimefin.model;

import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.domain.PhishingInfoVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhishingInfoDAOImpl implements PhishingInfoDAO {

    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.phishinginfo.mapper.";

    @Override
    public PhishingInfoVO isPhishingNumber(String number) {
        return sqlSession.selectOne(NS+"select", number);
    }
}
