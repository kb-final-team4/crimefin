package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;

import java.util.HashMap;
import java.util.List;

public interface BankingDAO {

    int insertBanking(BankingVO bankingVO);
    List<BankingVO> getBanking(HashMap map);

}
