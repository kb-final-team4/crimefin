package com.service.crimefin.service;

import com.service.crimefin.domain.BankingVO;

import java.util.List;

public interface BankingService {

    public int insertBanking(BankingVO bankingVO);
    public List<BankingVO> getBanking(BankingVO bankingVO);
    public List<BankingVO> getBankingByAccountId(BankingVO bankingVO);
    public List<BankingVO> getBankingByMemberId(BankingVO bankingVO);
    public int updateBanking(BankingVO bankingVO);
    public int deleteBanking(BankingVO bankingVO);
}
