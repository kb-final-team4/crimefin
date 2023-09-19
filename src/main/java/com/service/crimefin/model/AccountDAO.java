package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;

import java.util.List;

public interface AccountDAO {
    int insertAccount(AccountVO accountVO);
    int deleteAccount(AccountVO accountVO);
    List<AccountVO> getAccounts(String memberId);


}
