package com.service.crimefin.model;

import com.service.crimefin.domain.AccountVO;

public interface AccountDAO {
    int insertAccount(AccountVO accountVO);
    int deleteAccount(AccountVO accountVO);
}
