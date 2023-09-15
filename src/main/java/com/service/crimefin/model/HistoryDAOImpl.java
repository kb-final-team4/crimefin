package com.service.crimefin.model;

import com.service.crimefin.domain.PhishingInfoVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDAOImpl implements HistoryDAO {

    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.history.mapper.";


}
