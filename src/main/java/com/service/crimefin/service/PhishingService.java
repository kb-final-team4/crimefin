package com.service.crimefin.service;

import com.service.crimefin.domain.PhishingInfoVO;
import org.springframework.stereotype.Service;

public interface PhishingService {

    public PhishingInfoVO isPhishingNumber(String number);
}
