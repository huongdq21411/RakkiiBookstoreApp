package com.group8.rakkiibookstoreapp.model;

public class Voucher {
    private int voucherPhoto;
    private int voucherId;
    private String voucherTitle;
    private String voucherCategory;
    private String voucherCondition;
    String voucherTime;

    public Voucher(int voucherPhoto, int voucherId, String voucherTitle, String voucherCategory, String voucherCondition, String voucherTime) {
        this.voucherPhoto = voucherPhoto;
        this.voucherId = voucherId;
        this.voucherTitle = voucherTitle;
        this.voucherCategory = voucherCategory;
        this.voucherCondition = voucherCondition;
        this.voucherTime = voucherTime;
    }

    public int getVoucherPhoto() {
        return voucherPhoto;
    }

    public void setVoucherPhoto(int voucherPhoto) {
        this.voucherPhoto = voucherPhoto;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
    }

    public String getVoucherCategory() {
        return voucherCategory;
    }

    public void setVoucherCategory(String voucherCategory) {
        this.voucherCategory = voucherCategory;
    }

    public String getVoucherCondition() {
        return voucherCondition;
    }

    public void setVoucherCondition(String voucherCondition) {
        this.voucherCondition = voucherCondition;
    }

    public String getVoucherTime() {
        return voucherTime;
    }

    public void setVoucherTime(String voucherTime) {
        this.voucherTime = voucherTime;
    }

}
