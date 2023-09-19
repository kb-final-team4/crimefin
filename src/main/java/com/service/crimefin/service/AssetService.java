package com.service.crimefin.service;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;

public interface AssetService {
    int insertAccount(AccountVO accountVO);
    int deleteAccount(AccountVO accountVO);

    int insertBanking(BankingVO bankingVO);

}