package com.qf.entity;


import java.sql.Timestamp;

public class TOrder {
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String account;
  private Integer payType;
  private Integer carry;
  private double rebate;
  private java.sql.Timestamp createdate;//创建日期
  private String status;// 订单状态
  private String refundStatus;
  private double amount;//订单总金额
  private double fee;  //配送费
  private double ptotal;  //商品总金额
  private Integer quantity;//商品总数量
  private String paystatus;  // 订单支付状态
  private String updateAmount;
  private String expressCode;//提交订单时候选中的配送方式
  private String expressName;//配送方式名称
  private String otherRequirement;//客户附加要求
  private String remark;//订单支付时候显示的文字


  private String expressNo;//物流单号
  private String expressCompanyName;//物流公司名称
  private String lowStocks;//n:库存不足；y:库存充足。默认n
  private String confirmSendProductRemark;
  private String closedComment;//此订单的所有订单项对应的商品都进行了评论，则此值为y，表示此订单的评论功能已经关闭
  private Integer score;//订单能获得的积分
  private int amountExchangeScore;//订单总兑换积分

  /*** 订单状态:已成功下单*/
  public static final String order_status_init = "init";
  /*** 订单状态:已审核*/
  public static final String order_status_pass = "pass";
  /*** 订单状态:已发货*/
  public static final String order_status_send = "send";
  /*** 订单状态:已签收*/
  public static final String order_status_sign = "sign";
  /*** 订单状态:已取消*/
  public static final String order_status_cancel = "cancel";
  /*** 订单状态:已归档*/
  public static final String order_status_file = "file";

  public static final String order_status_init_chinese = "已下单";
  public static final String order_status_pass_chinese = "已审核";
  public static final String order_status_send_chinese = "已发货";
  public static final String order_status_sign_chinese = "已签收";
  public static final String order_status_cancel_chinese = "已取消";
  public static final String order_status_file_chinese = "已归档";

  /** 订单支付状态:未支付*/
  public static final String order_paystatus_n = "n";//
  /** 订单支付状态:部分支付*/
  public static final String order_paystatus_p = "p";//
  /** 订单支付状态:全部支付*/
  public static final String order_paystatus_y = "y";//

  /**
   * 订单是否缺货状态
   */
  public static final String order_lowStocks_y = "y";//订单中存在商品缺货
  public static final String order_lowStocks_n = "n";//不缺货

  /**
   * 订单评论状态是否关闭
   */
  public static String order_closedComment_y = "y";//已关闭
  public static String order_closedComment_n = "n";//未关闭

  public void clear() {
    id = null;
    account = null;
    rebate = 0d;
    remark = null;
    status = null;
    refundStatus = null;
    paystatus = null;
    lowStocks = null;
    amount = 0.0;
    amountExchangeScore = 0;
    fee = 0.0;
    createdate = null;
    ptotal = 0.0;
    quantity = 0;
//		picture = null;
    expressCode = null;
    expressName = null;
    expressNo = null;//物流单号
    expressCompanyName = null;//物流公司名称
    confirmSendProductRemark = null;
    otherRequirement = null;
    closedComment = null;
    score = 0;

  }


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

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
    this.account = account;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Integer getCarry() {
    return carry;
  }

  public void setCarry(Integer carry) {
    this.carry = carry;
  }

  public double getRebate() {
    return rebate;
  }

  public void setRebate(double rebate) {
    this.rebate = rebate;
  }

  public Timestamp getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Timestamp createdate) {
    this.createdate = createdate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public double getPtotal() {
    return ptotal;
  }

  public void setPtotal(double ptotal) {
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
    this.paystatus = paystatus;
  }

  public String getUpdateAmount() {
    return updateAmount;
  }

  public void setUpdateAmount(String updateAmount) {
    this.updateAmount = updateAmount;
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getOtherRequirement() {
    return otherRequirement;
  }

  public void setOtherRequirement(String otherRequirement) {
    this.otherRequirement = otherRequirement;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getExpressCompanyName() {
    return expressCompanyName;
  }

  public void setExpressCompanyName(String expressCompanyName) {
    this.expressCompanyName = expressCompanyName;
  }

  public String getLowStocks() {
    return lowStocks;
  }

  public void setLowStocks(String lowStocks) {
    this.lowStocks = lowStocks;
  }

  public String getConfirmSendProductRemark() {
    return confirmSendProductRemark;
  }

  public void setConfirmSendProductRemark(String confirmSendProductRemark) {
    this.confirmSendProductRemark = confirmSendProductRemark;
  }

  public String getClosedComment() {
    return closedComment;
  }

  public void setClosedComment(String closedComment) {
    this.closedComment = closedComment;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public int getAmountExchangeScore() {
    return amountExchangeScore;
  }

  public void setAmountExchangeScore(int amountExchangeScore) {
    this.amountExchangeScore = amountExchangeScore;
  }
}
