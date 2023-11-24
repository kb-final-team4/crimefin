package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;

import java.util.List;

public interface AccountDAO {
    int insertAccount(AccountVO accountVO);
    void deleteLimitBanking(String bankingId);
    void deleteBanking(String accountNum);
    int deleteAccount(String accountNum);
    int updateNickNameAndLimit(AccountVO accountVO);
    List<AccountVO> getAccounts(String memberId);
    AccountVO findByAccountNum(String accountNum);
}
