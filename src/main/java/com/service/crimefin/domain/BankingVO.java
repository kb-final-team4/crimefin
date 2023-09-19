package com.service.crimefin.domain;

public class BankingVO {
    private String bankingId; //거래 아이디
    private String memberId; //사용자 아이디
    private String accountId; //계좌 아이디
    private String bankingDate; //거래 일자s
    private int deposit; //예금액
    private String depositName; //입금자 명
    private int withdrawal; //출금액
    private String withdrawalTo; //출금


    public BankingVO() {}

    public BankingVO(String bankingId, String memberId, String accountId, String bankingDate, int deposit, String depositName, int withdrawal, String withdrawalTo) {
        this.bankingId = bankingId;
        this.memberId = memberId;
        this.accountId = accountId;
        this.bankingDate = bankingDate;
        this.deposit = deposit;
        this.depositName = depositName;
        this.withdrawal = withdrawal;
        this.withdrawalTo = withdrawalTo;
    }

    public String getBankingId() {
        return bankingId;
    }

    public void setBankingId(String bankingId) {
        this.bankingId = bankingId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankingDate() {
        return bankingDate;
    }

    public void setBankingDate(String bankingDate) {
        this.bankingDate = bankingDate;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getWithdrawalTo() {
        return withdrawalTo;
    }

    public void setWithdrawalTo(String withdrawalTo) {
        this.withdrawalTo = withdrawalTo;
    }

    @Override
    public String toString() {
        return "BankingVO{" +
                "bankingId='" + bankingId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", bankingDate='" + bankingDate + '\'' +
                ", deposit='" + deposit + '\'' +
                ", depositName='" + depositName + '\'' +
                ", withdrawal='" + withdrawal + '\'' +
                ", withdrawalTo='" + withdrawalTo + '\'' +
                '}';
    }
}
