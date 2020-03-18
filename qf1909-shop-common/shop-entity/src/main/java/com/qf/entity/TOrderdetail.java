package com.qf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TOrderdetail implements Serializable {
    private Integer id;

    private Integer orderid;

    private Integer productid;

    private BigDecimal price;

    private Integer number;

    private String productname;

    private BigDecimal fee;

    private BigDecimal total0;

    private String iscomment;

    private String lowstocks;

    private Integer score;

    private String specinfo;

    private String giftid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTotal0() {
        return total0;
    }

    public void setTotal0(BigDecimal total0) {
        this.total0 = total0;
    }

    public String getIscomment() {
        return iscomment;
    }

    public void setIscomment(String iscomment) {
        this.iscomment = iscomment == null ? null : iscomment.trim();
    }

    public String getLowstocks() {
        return lowstocks;
    }

    public void setLowstocks(String lowstocks) {
        this.lowstocks = lowstocks == null ? null : lowstocks.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSpecinfo() {
        return specinfo;
    }

    public void setSpecinfo(String specinfo) {
        this.specinfo = specinfo == null ? null : specinfo.trim();
    }

    public String getGiftid() {
        return giftid;
    }

    public void setGiftid(String giftid) {
        this.giftid = giftid == null ? null : giftid.trim();
    }
}