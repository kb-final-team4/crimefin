package com.service.crimefin.domain;

public class AccountVO {
    private String memberId; //사용자 아이디
    private String accountNum; //계좌 번호
    private String bankName; //은행명
    private String nickName; //계좌 닉네임
    private int balance; //잔고
    private int limit;//위험 알림 제한 금액

    public AccountVO(){}
    public AccountVO(String memberId, String accountNum, String bankName, String nickName, int balance, int limit) {
        this.memberId = memberId;
        this.accountNum = accountNum;
        this.bankName = bankName;
        this.nickName = nickName;
        this.balance = balance;
        this.limit = limit;
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

    public String getBank() {
        return bankName;
    }

    public void setBank(String bankName) {
        this.bankName = bankName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
                ", memberId='" + memberId + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", bankName='" + bankName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", balance=" + balance +
                ", limit=" + limit +
                '}';
    }
}
