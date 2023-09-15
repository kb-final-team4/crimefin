package com.service.crimefin.domain;

public class HistoryVO {
    private long historyId;
    private String memberId;
    private String historyDate;
    private String phishingNum;
    private String phishingLink;

    public HistoryVO() {}

    public HistoryVO(long historyId, String memberId, String historyDate, String phishingNum, String phishingLink) {
        super();
        this.historyId = historyId;
        this.memberId = memberId;
        this.historyDate = historyDate;
        this.phishingNum = phishingNum;
        this.phishingLink = phishingLink;
    }

    public long getHistoryId() {
        return historyId;
    }
    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getHistoryDate() {
        return historyDate;
    }
    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }
    public String getPhishingNum() {
        return phishingNum;
    }
    public void setPhishingNum(String phishingNum) {
        this.phishingNum = phishingNum;
    }
    public String getPhishingLink() {
        return phishingLink;
    }
    public void setPhishingLink(String phishingLink) {
        this.phishingLink = phishingLink;
    }

    @Override
    public String toString() {
        return "HistoryVO [historyId=" + historyId + ", memberId=" + memberId + ", historyDate=" + historyDate
                + ", phishingNum=" + phishingNum + ", phishingLink=" + phishingLink + "]";
    }
}