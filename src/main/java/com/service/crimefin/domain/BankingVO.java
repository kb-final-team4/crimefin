package com.service.crimefin.domain;

public class BankingVO {
    private String bankingId; //거래 아이디
    private String memberId; //사용자 아이디
    private String accountId; //계좌 아이디
    private String bankingDate; //거래 일자s
    private String deposit; //예금액
    private String depositName; //입금자 명
    private String withDrawl; //출금액
    private String withDrawlTo; //출금


    public BankingVO() {}

    public BankingVO(String bankingId, String memberId, String accountId, String bankingDate, String deposit, String depositName, String withDrawl, String withDrawlTo) {
        this.bankingId = bankingId;
        this.memberId = memberId;
        this.accountId = accountId;
        this.bankingDate = bankingDate;
        this.deposit = deposit;
        this.depositName = depositName;
        this.withDrawl = withDrawl;
        this.withDrawlTo = withDrawlTo;
    }

    public String getBankingId() {
        return bankingId;
    }

    public void setBankingId(String bankingId) {
        this.bankingId = bankingId;
    }

    public String getUserId() {
        return memberId;
    }

    public void setUserId(String memberId) {
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

    @Override
    public String toString() {
        return "TradeVO{" +
                "bankingId='" + bankingId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", bankingDate='" + bankingDate + '\'' +
                ", deposit='" + deposit + '\'' +
                ", depositName='" + depositName + '\'' +
                ", withDrawl='" + withDrawl + '\'' +
                ", withDrawlTo='" + withDrawlTo + '\'' +
                '}';
    }
}
