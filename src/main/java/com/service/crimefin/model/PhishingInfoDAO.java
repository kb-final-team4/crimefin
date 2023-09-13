package com.service.crimefin.model;

import com.service.crimefin.domain.PhishingInfoVO;

public interface PhishingInfoDAO {

    public PhishingInfoVO isPhishingNumber(String number);
}
