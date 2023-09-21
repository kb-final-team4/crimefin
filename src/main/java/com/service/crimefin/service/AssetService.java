package com.service.crimefin.service;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;

import java.util.HashMap;
import java.util.List;

public interface AssetService {

    int insertAccount(AccountVO accountVO);
    int insertBanking(BankingVO bankingVO);
    void deleteBanking(String accountNum);
    int deleteAccount(String accountNum);
    int updateNickNameAndLimit(AccountVO accountVO);
    List<AccountVO> getAccounts(String memberId);
    List<BankingVO> getBanking(HashMap map);

}