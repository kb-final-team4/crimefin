package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;

public interface BankingDAO {

    int insertBanking(BankingVO bankingVO);
}
