package com.service.crimefin.domain;

public class ChecklistVO {
    private int checkId;
    private String memberId;
    private int idx;

    public ChecklistVO(){}

    public ChecklistVO(int checkId, String memberId, int type, int idx) {
        this.checkId = checkId;
        this.memberId = memberId;
        this.idx = idx;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "ChecklistVO{" +
                "checkId=" + checkId +
                ", memberId='" + memberId + '\'' +
                ", idx=" + idx +
                '}';
    }
}
