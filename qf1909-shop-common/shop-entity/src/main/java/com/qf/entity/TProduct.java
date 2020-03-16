package com.qf.entity;


import java.io.Serializable;


public class TProduct implements Serializable {

  private Integer pid;
  private String pname;
  private double price;
  private double salePrice;
  private Integer typeId;
  private Integer status;
  private String pimage;
  private Integer flag;
  private String createTime;
  private String updateTime;
  private Integer createUser;
  private Integer updateUser;

  public TProduct(Integer pid, String pname, double price, double salePrice, Integer typeId, Integer status, String pimage, Integer flag, String createTime, String updateTime, Integer createUser, Integer updateUser) {
    this.pid = pid;
    this.pname = pname;
    this.price = price;
    this.salePrice = salePrice;
    this.typeId = typeId;
    this.status = status;
    this.pimage = pimage;
    this.flag = flag;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.createUser = createUser;
    this.updateUser = updateUser;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPimage() {
    return pimage;
  }

  public void setPimage(String pimage) {
    this.pimage = pimage;
  }

  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Integer createUser) {
    this.createUser = createUser;
  }

  public Integer getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(Integer updateUser) {
    this.updateUser = updateUser;
  }
}
