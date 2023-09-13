package com.service.crimefin.service;

import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.model.PhishingInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhishingServiceImpl implements PhishingService {

    @Autowired
    private PhishingInfoDAO phishingInfoDAO;

    @Override
    public PhishingInfoVO isPhishingNumber(String phoneNum) {
        return phishingInfoDAO.isPhishingNumber(phoneNum);
    }
}
