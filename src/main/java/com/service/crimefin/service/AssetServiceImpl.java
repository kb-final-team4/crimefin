package com.service.crimefin.service;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.model.AccountDAO;
import com.service.crimefin.model.BankingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BankingDAO bankingDAO;

    @Override
    public int insertAccount(AccountVO accountVO) {

        return accountDAO.insertAccount(accountVO);
    }

    @Override
    public int insertBanking(BankingVO bankingVO) {

        return bankingDAO.insertBanking(bankingVO);
    }

    @Override
    public void deleteBanking(String accountNum) {
        accountDAO.deleteBanking(accountNum);
    }

    @Override
    public int deleteAccount(String accountNum) {
        return accountDAO.deleteAccount(accountNum);
    }

    @Override
    public int updateNickNameAndLimit(AccountVO accountVO) {
        return accountDAO.updateNickNameAndLimit(accountVO);
    }

    @Override
    public List<AccountVO> getAccounts(String memberId) {return accountDAO.getAccounts(memberId); }

    @Override
    public List<BankingVO> getBanking(HashMap map) { return bankingDAO.getBanking(map); }

    @Override
    public List<BankingVO> getMaxBalanceBanking(String memberId) {
        return bankingDAO.getMaxBalanceBanking(memberId);
    }

}