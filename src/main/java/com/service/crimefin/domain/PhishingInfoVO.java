package com.service.crimefin.domain;

public class PhishingInfoVO {
    private String phishingId; //피싱 아이디
    private String phishingNum; //피싱 번호
    private String phishingNumInfo; //피싱 번호 설명
    private String phishingLink; //피싱 링크

    public PhishingInfoVO() {}

    public PhishingInfoVO(String phishingId, String phishingNum, String phishingNumInfo, String phishingLink) {
        this.phishingId = phishingId;
        this.phishingNum = phishingNum;
        this.phishingNumInfo = phishingNumInfo;
        this.phishingLink = phishingLink;
    }

    public String getPhishingId() {
        return phishingId;
    }

    public void setPhishingId(String phishingId) {
        this.phishingId = phishingId;
    }

    public String getPhishingNum() {
        return phishingNum;
    }

    public void setPhishingNum(String phishingNum) {
        this.phishingNum = phishingNum;
    }

    public String getPhishingNumInfo() {
        return phishingNumInfo;
    }

    public void setPhishingNumInfo(String phishingNumInfo) {
        this.phishingNumInfo = phishingNumInfo;
    }

    public String getPhishingLink() {
        return phishingLink;
    }

    public void setPhishingLink(String phishingLink) {
        this.phishingLink = phishingLink;
    }

    @Override
    public String toString() {
        return "PishingInfo{" +
                "phishingId='" + phishingId + '\'' +
                ", phishingNum='" + phishingNum + '\'' +
                ", phishingNumInfo='" + phishingNumInfo + '\'' +
                ", phishingLink='" + phishingLink + '\'' +
                '}';
    }
}
