package com.demo.vo;

import java.io.Serializable;


public class Gys implements Serializable {
    private Long id;//主键
    private String gysNo;//编号
    private String gysName;//名称
    private String gysContact;//联系人
    private String gysPhone;//电话
    private String gysText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getGysNo() {
        return gysNo;
    }

    public void setGysNo(String gysNo) {
        this.gysNo = gysNo;
    }
    public String getGysName() {
        return gysName;
    }

    public void setGysName(String gysName) {
        this.gysName = gysName;
    }
    public String getGysContact() {
        return gysContact;
    }

    public void setGysContact(String gysContact) {
        this.gysContact = gysContact;
    }
    public String getGysPhone() {
        return gysPhone;
    }

    public void setGysPhone(String gysPhone) {
        this.gysPhone = gysPhone;
    }
    public String getGysText() {
        return gysText;
    }

    public void setGysText(String gysText) {
        this.gysText = gysText;
    }
}
