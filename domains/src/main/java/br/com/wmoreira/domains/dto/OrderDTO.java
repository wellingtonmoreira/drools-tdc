package br.com.wmoreira.domains.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO implements Serializable {
    private String orderDate;
    private ProductDTO[] products;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private int discountPercentage;

    public OrderDTO() {}

    public OrderDTO(String orderDate, ProductDTO[] products, BigDecimal totalPrice, BigDecimal discount, int discountPercentage) {
        this.orderDate = orderDate;
        this.products = products;
        this.totalPrice = totalPrice;
        if (discount.compareTo(BigDecimal.ZERO) > 0 && discountPercentage > 0) {
            this.discount = discount;
            this.discountPercentage = discountPercentage;
        }
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ProductDTO[] getProducts() {
        return products;
    }

    public void setProducts(ProductDTO[] products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
