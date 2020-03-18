package com.qf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TOrder implements Serializable {
    private Integer id;
    private String account;
    private Integer paytype;
    private Integer carry;
    private BigDecimal rebate;
    private Date createdate;
    private String status;
    private String refundstatus;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal ptotal;
    private Integer quantity;
    private String paystatus;
    private String updateamount;
    private String expresscode;
    private String expressname;
    private String otherrequirement;
    private String remark;
    private String expressno;
    private String expresscompanyname;
    private String lowstocks;
    private String confirmsendproductremark;
    private String closedcomment;
    private Integer score;
    private Integer amountexchangescore;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getCarry() {
        return carry;
    }

    public void setCarry(Integer carry) {
        this.carry = carry;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(String refundstatus) {
        this.refundstatus = refundstatus == null ? null : refundstatus.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getPtotal() {
        return ptotal;
    }

    public void setPtotal(BigDecimal ptotal) {
        this.ptotal = ptotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public String getUpdateamount() {
        return updateamount;
    }

    public void setUpdateamount(String updateamount) {
        this.updateamount = updateamount == null ? null : updateamount.trim();
    }

    public String getExpresscode() {
        return expresscode;
    }

    public void setExpresscode(String expresscode) {
        this.expresscode = expresscode == null ? null : expresscode.trim();
    }

    public String getExpressname() {
        return expressname;
    }

    public void setExpressname(String expressname) {
        this.expressname = expressname == null ? null : expressname.trim();
    }

    public String getOtherrequirement() {
        return otherrequirement;
    }

    public void setOtherrequirement(String otherrequirement) {
        this.otherrequirement = otherrequirement == null ? null : otherrequirement.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno == null ? null : expressno.trim();
    }

    public String getExpresscompanyname() {
        return expresscompanyname;
    }

    public void setExpresscompanyname(String expresscompanyname) {
        this.expresscompanyname = expresscompanyname == null ? null : expresscompanyname.trim();
    }

    public String getLowstocks() {
        return lowstocks;
    }

    public void setLowstocks(String lowstocks) {
        this.lowstocks = lowstocks == null ? null : lowstocks.trim();
    }

    public String getConfirmsendproductremark() {
        return confirmsendproductremark;
    }

    public void setConfirmsendproductremark(String confirmsendproductremark) {
        this.confirmsendproductremark = confirmsendproductremark == null ? null : confirmsendproductremark.trim();
    }

    public String getClosedcomment() {
        return closedcomment;
    }

    public void setClosedcomment(String closedcomment) {
        this.closedcomment = closedcomment == null ? null : closedcomment.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getAmountexchangescore() {
        return amountexchangescore;
    }

    public void setAmountexchangescore(Integer amountexchangescore) {
        this.amountexchangescore = amountexchangescore;
    }
}