package com.service.crimefin.service;

import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.model.BankingDAO;
import com.service.crimefin.model.PhishingInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankingServiceImpl implements BankingService {

    @Autowired
    private BankingDAO bankingDAO;

    @Override
    public int insertBanking(BankingVO bankingVO) {
        return bankingDAO.insertBanking(bankingVO);
    }

    @Override
    public List<BankingVO> getBanking(BankingVO bankingVO) {
        return bankingDAO.getBanking(bankingVO);
    }

    @Override
    public List<BankingVO> getBankingByAccountId(BankingVO bankingVO) {
        return bankingDAO.getBankingByAccountId(bankingVO);
    }

    @Override
    public List<BankingVO> getBankingByMemberId(BankingVO bankingVO) {
        return bankingDAO.getBankingByMemberId(bankingVO);
    }

    @Override
    public int updateBanking(BankingVO bankingVO) {
        return bankingDAO.updateBanking(bankingVO);
    }

    @Override
    public int deleteBanking(BankingVO bankingVO) {
        return bankingDAO.deleteBanking(bankingVO);
    }
}
