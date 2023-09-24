package com.service.crimefin.domain;

public class NoticeVO {
    private String noticeId;
    private String memberId;
    private String time;
    private String accountNum;
    private String bankName;
    private int deposit;
    private int withdrawal;

    public NoticeVO(){}

    public NoticeVO(String noticeId, String memberId, String time, String accountNum, String bankName, int deposit, int withdrawal) {
        this.noticeId = noticeId;
        this.memberId = memberId;
        this.time = time;
        this.accountNum = accountNum;
        this.bankName = bankName;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    @Override
    public String toString() {
        return "NoticeVO{" +
                "noticeId='" + noticeId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", time='" + time + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", bankName='" + bankName + '\'' +
                ", deposit=" + deposit +
                ", withdrawal=" + withdrawal +
                '}';
    }
}