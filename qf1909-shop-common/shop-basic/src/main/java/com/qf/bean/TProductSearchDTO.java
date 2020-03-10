package com.qf.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author smj
 * @date 2020/3/10 - 19:35
 */
public class TProductSearchDTO implements Serializable {

    private Long id;
    private String tProductName;
    private BigDecimal tProductSalePrice;
    private String tProductPimage;
    private String tProductPdesc;

    public TProductSearchDTO() {
    }

    public TProductSearchDTO(Long id, String tProductName, BigDecimal tProductSalePrice, String tProductPimage, String tProductPdesc) {
        this.id = id;
        this.tProductName = tProductName;
        this.tProductSalePrice = tProductSalePrice;
        this.tProductPimage = tProductPimage;
        this.tProductPdesc = tProductPdesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettProductName() {
        return tProductName;
    }

    public void settProductName(String tProductName) {
        this.tProductName = tProductName;
    }

    public BigDecimal gettProductSalePrice() {
        return tProductSalePrice;
    }

    public void settProductSalePrice(BigDecimal tProductSalePrice) {
        this.tProductSalePrice = tProductSalePrice;
    }

    public String gettProductPimage() {
        return tProductPimage;
    }

    public void settProductPimage(String tProductPimage) {
        this.tProductPimage = tProductPimage;
    }

    public String gettProductPdesc() {
        return tProductPdesc;
    }

    public void settProductPdesc(String tProductPdesc) {
        this.tProductPdesc = tProductPdesc;
    }
}
