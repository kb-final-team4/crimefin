package com.service.crimefin.service;

import com.service.crimefin.domain.ChecklistVO;
import com.service.crimefin.domain.PhishingInfoVO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

public interface PhishingService {

    public PhishingInfoVO isPhishingNumber(String number);

    public int isPhishingLink(String url) throws Exception;

    public int insertChecklist(ChecklistVO checklistVO) throws SQLException;
}
