package com.example.cafemanager.order;

public class Member {
    private long memberId;
    private String memberName;
    private String fcmToken;

    public Member() {
    }

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Member(String memberName, String fcmToken) {
        this.memberName = memberName;
        this.fcmToken = fcmToken;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
