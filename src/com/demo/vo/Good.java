package com.demo.vo;

import java.io.Serializable;


public class Good implements Serializable {
    private Long id;//主键
    private String wupinName;//名称
    private String wupinNo;//编号
    private String wupinCount;//数量
    private String wupinType;//类别
    private String wupinGys;//供应商
    private String wupinGuige;//规格
    private String wupinPrice;//采购价
    private String wupinText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getWupinName() {
        return wupinName;
    }

    public void setWupinName(String wupinName) {
        this.wupinName = wupinName;
    }
    public String getWupinNo() {
        return wupinNo;
    }

    public void setWupinNo(String wupinNo) {
        this.wupinNo = wupinNo;
    }
    public String getWupinCount() {
        return wupinCount;
    }

    public void setWupinCount(String wupinCount) {
        this.wupinCount = wupinCount;
    }
    public String getWupinType() {
        return wupinType;
    }

    public void setWupinType(String wupinType) {
        this.wupinType = wupinType;
    }
    public String getWupinGys() {
        return wupinGys;
    }

    public void setWupinGys(String wupinGys) {
        this.wupinGys = wupinGys;
    }
    public String getWupinGuige() {
        return wupinGuige;
    }

    public void setWupinGuige(String wupinGuige) {
        this.wupinGuige = wupinGuige;
    }
    public String getWupinPrice() {
        return wupinPrice;
    }

    public void setWupinPrice(String wupinPrice) {
        this.wupinPrice = wupinPrice;
    }
    public String getWupinText() {
        return wupinText;
    }

    public void setWupinText(String wupinText) {
        this.wupinText = wupinText;
    }
}
