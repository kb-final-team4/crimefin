package com.service.crimefin.model;

import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;

import java.util.HashMap;
import java.util.List;

public interface BankingDAO {
    public int insertBanking(BankingVO bankingVO);

    public List<BankingVO> getBanking(HashMap map);

    public List<BankingVO> getMaxBalanceBanking(String memberId);
}
