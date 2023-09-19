package com.service.crimefin.model;

import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;

import java.util.List;

public interface BankingDAO {
    public int insertBanking(BankingVO bankingVO);

    public List<BankingVO> getBanking(BankingVO bankingVO);
    public List<BankingVO> getBankingByAccountId(BankingVO bankingVO);
    public List<BankingVO> getBankingByMemberId(BankingVO bankingVO);

    public int deleteBanking(BankingVO bankingVO);
    public int updateBanking(BankingVO bankingVO);

}
