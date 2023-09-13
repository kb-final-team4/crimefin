package com.service.crimefin.domain;

import java.util.ArrayList;

public class MemberVO {
    private String memberId; //사용자 아이디
    private String email; //이메일
    private String password; //비밀번호
    private String name; //이름
    private String phone; //핸드폰 번호
    private String address; //주소
    private int sex; //성별 (1=남자, 2=여자)
    private String regDate; //가입 날짜
    private int messageOk; //메세지 수신 동의 여부  1=동의, 0=비동의

    private ArrayList<BankingVO> transactionHistory; //사용자의 거래 이력
    private ArrayList<HistoryVO> phishingHistory; //사용자의 피싱 이력


    public MemberVO(){}
    public MemberVO(String memberId, String email, String password, String name, String phone, String address, int sex, String regDate, int messageOk) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
        this.regDate = regDate;
        this.messageOk = messageOk;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getMessageOk() {
        return messageOk;
    }

    public void setMessageOk(int messageOk) {
        this.messageOk = messageOk;
    }

    public ArrayList<BankingVO> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<BankingVO> transactionHistory) {
        transactionHistory = transactionHistory;
    }

    public ArrayList<HistoryVO> getPhishingHistory() {
        return phishingHistory;
    }

    public void setPhishingHistory(ArrayList<HistoryVO> phishingHistory) {
        this.phishingHistory = phishingHistory;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "memberId='" + memberId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", regDate='" + regDate + '\'' +
                ", messageOk=" + messageOk +
                ", transactionHistory=" + transactionHistory +
                ", phishingHistory=" + phishingHistory +
                '}';
    }
}
