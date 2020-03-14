package com.qf.entity;


public class TOrderdetail {

  private Integer id;
  private Integer orderId;
  private Integer productId;
  private double price;
  private long number;
  private String productName;
  private double fee;
  private double total0;
  private String isComment;
  private String lowStocks;
  private Integer score;
  private String specInfo;
  private String giftId;


//  @Deprecated
//  private int score;

  /**
   * 订单项是否存在商品库存不足
   */
  public static final String orderdetail_lowstocks_n = "n";// 不存在
  public static final String orderdetail_lowstocks_y = "y";// 库存不足

  /**
   * 订单项是否已评论
   */
  public static final String orderdetail_isComment_n = "n";// 未评论
  public static final String orderdetail_isComment_y = "y";// 已评论

  public void clear() {
    id = null;
    orderId = 0;
    productId = 0;
    giftId = null;

    price = 0;
    number = 0;

    fee = 0;
    isComment = null;
    productName = null;

    total0 = 0;
    lowStocks = null;
    specInfo = null;
    score = 0;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public double getTotal0() {
    return total0;
  }

  public void setTotal0(double total0) {
    this.total0 = total0;
  }

  public String getIsComment() {
    return isComment;
  }

  public void setIsComment(String isComment) {
    this.isComment = isComment;
  }

  public String getLowStocks() {
    return lowStocks;
  }

  public void setLowStocks(String lowStocks) {
    this.lowStocks = lowStocks;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getSpecInfo() {
    return specInfo;
  }

  public void setSpecInfo(String specInfo) {
    this.specInfo = specInfo;
  }

  public String getGiftId() {
    return giftId;
  }

  public void setGiftId(String giftId) {
    this.giftId = giftId;
  }
}
