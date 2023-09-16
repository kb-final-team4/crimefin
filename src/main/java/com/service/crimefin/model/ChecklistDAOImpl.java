package com.service.crimefin.model;

import com.service.crimefin.domain.ChecklistVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class ChecklistDAOImpl implements ChecklistDAO{
    @Autowired
    private SqlSession sqlSession;
    public static final String NS = "sql.checklist.mapper.";
    @Override
    public int insertChecklist(ChecklistVO checklistVO) throws SQLException {
        return sqlSession.insert(NS+"insertChecklist", checklistVO);
    }
}
