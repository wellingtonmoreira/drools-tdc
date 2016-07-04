package br.com.wmoreira.domains.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO implements Serializable {
    private String orderDate;
    private ProductDTO[] products;
    private BigDecimal totalPrice;

    public OrderDTO() {}

    public OrderDTO(String orderDate, ProductDTO[] products, BigDecimal totalPrice) {
        this.orderDate = orderDate;
        this.products = products;
        this.totalPrice = totalPrice;
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
}
