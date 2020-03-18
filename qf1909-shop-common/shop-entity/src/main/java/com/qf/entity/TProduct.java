package com.qf.entity;


import java.io.Serializable;
import java.sql.Timestamp;

public class TProduct implements Serializable {
  private long pid;
  private String pname;
  private double price;
  private double salePrice;
  private long typeId;
  private long status;
  private String pimage;
  private long flag;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long createUser;
  private long updateUser;

  public long getPid() {
    return pid;
  }

  public String getPname() {
    return pname;
  }

  public double getPrice() {
    return price;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public long getTypeId() {
    return typeId;
  }

  public long getStatus() {
    return status;
  }

  public String getPimage() {
    return pimage;
  }

  public long getFlag() {
    return flag;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public long getCreateUser() {
    return createUser;
  }

  public long getUpdateUser() {
    return updateUser;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  public void setTypeId(long typeId) {
    this.typeId = typeId;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  public void setPimage(String pimage) {
    this.pimage = pimage;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public void setCreateUser(long createUser) {
    this.createUser = createUser;
  }

  public void setUpdateUser(long updateUser) {
    this.updateUser = updateUser;
  }
}
