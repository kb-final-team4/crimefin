package com.service.crimefin.domain;

public class AccountVO {
    private String accountId; //계좌 아이디
    private String memberId; //사용자 아이디
    private String accountNum; //계좌 번호
    private String bankName; //은행명
    private int balance; //잔고
    private int limit;//위험 알림 제한 금액

    public AccountVO(String accountId, String memberId, String accountNum, String bankName, int balance, int limit) {
        this.accountId = accountId;
        this.memberId = memberId;
        this.accountNum = accountNum;
        this.bankName = bankName;
        this.balance = balance;
        this.limit = limit;
    }

    public AccountVO() {}

    public AccountVO(String accountId, String memberId, String accountNum, String bankName, int balance) {
        this.accountId = accountId;
        this.memberId = memberId;
        this.accountNum = accountNum;
        this.bankName = bankName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBank(String bankName) {
        this.bankName = bankName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
                "accountId='" + accountId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", bankName='" + bankName + '\'' +
                ", balance=" + balance +
                ", limit=" + limit +
                '}';
    }
}
