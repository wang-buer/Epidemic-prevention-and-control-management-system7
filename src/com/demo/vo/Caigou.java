package com.demo.vo;

import java.io.Serializable;


public class Caigou implements Serializable {
    private Long id;//主键
    private String caigouName;//物品名称
    private String caigouType;//类别
    private String caigouCount;//数量
    private String caigouDate;//创建时间
    private String caigouText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCaigouName() {
        return caigouName;
    }

    public void setCaigouName(String caigouName) {
        this.caigouName = caigouName;
    }
    public String getCaigouType() {
        return caigouType;
    }

    public void setCaigouType(String caigouType) {
        this.caigouType = caigouType;
    }
    public String getCaigouCount() {
        return caigouCount;
    }

    public void setCaigouCount(String caigouCount) {
        this.caigouCount = caigouCount;
    }
    public String getCaigouDate() {
        return caigouDate;
    }

    public void setCaigouDate(String caigouDate) {
        this.caigouDate = caigouDate;
    }
    public String getCaigouText() {
        return caigouText;
    }

    public void setCaigouText(String caigouText) {
        this.caigouText = caigouText;
    }
}
