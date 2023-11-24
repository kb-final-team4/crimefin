package com.service.crimefin.model;

import com.service.crimefin.domain.ChecklistVO;

import java.sql.SQLException;

public interface ChecklistDAO {
    public int insertChecklist(ChecklistVO checklistVO) throws SQLException;
}