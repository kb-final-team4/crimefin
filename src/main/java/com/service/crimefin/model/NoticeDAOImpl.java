package com.service.crimefin.model;

import com.service.crimefin.domain.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.noticelist.mapper.";
    @Override
    public int insertNotice(NoticeVO noticeVO) {
        return sqlSession.insert(NS+"insertNotice", noticeVO);
    }
}
