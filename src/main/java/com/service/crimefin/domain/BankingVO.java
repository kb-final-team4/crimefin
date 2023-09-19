package com.service.crimefin.domain;

public class BankingVO {
    private String bankingId; //거래 아이디
    private String memberId; //사용자 아이디
    private String accountNum; //계좌 번호
    private String bankingDate; //거래 일자
    private String deposit; //예금액
    private String depositName; //입금자 명
    private String withDrawl; //출금액
    private String withDrawlTo; //출금
    private String balance; //잔고


    public BankingVO() {}

    public BankingVO(String bankingId, String memberId, String accountNum, String bankingDate, String deposit, String depositName, String withDrawl, String withDrawlTo, String balance) {
        this.bankingId = bankingId;
        this.memberId = memberId;
        this.accountNum = accountNum;
        this.bankingDate = bankingDate;
        this.deposit = deposit;
        this.depositName = depositName;
        this.withDrawl = withDrawl;
        this.withDrawlTo = withDrawlTo;
        this.balance = balance;
    }

    public String getBankingId() {
        return bankingId;
    }

    public void setBankingId(String bankingId) {
        this.bankingId = bankingId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getBankingDate() {
        return bankingDate;
    }

    public void setBankingDate(String bankingDate) {
        this.bankingDate = bankingDate;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public String getWithDrawl() {
        return withDrawl;
    }

    public void setWithDrawl(String withDrawl) {
        this.withDrawl = withDrawl;
    }

    public String getWithDrawlTo() {
        return withDrawlTo;
    }

    public void setWithDrawlTo(String withDrawlTo) {
        this.withDrawlTo = withDrawlTo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankingVO{" +
                "bankingId='" + bankingId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", bankingDate='" + bankingDate + '\'' +
                ", deposit='" + deposit + '\'' +
                ", depositName='" + depositName + '\'' +
                ", withDrawl='" + withDrawl + '\'' +
                ", withDrawlTo='" + withDrawlTo + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
