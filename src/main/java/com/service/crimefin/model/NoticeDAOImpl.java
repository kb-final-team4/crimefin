package com.service.crimefin.model;

import com.service.crimefin.domain.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
    @Autowired
    private SqlSession sqlSession;

    public static final String NS = "sql.noticelist.mapper.";
    @Override
    public int insertNotice(NoticeVO noticeVO) {
        return sqlSession.insert(NS+"insertNotice", noticeVO);
    }

    @Override
    public List<NoticeVO> getNotice(String memberId) {
        return sqlSession.selectList(NS+"getNotice", memberId);
    }
}
