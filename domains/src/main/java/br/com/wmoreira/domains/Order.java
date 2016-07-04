package br.com.wmoreira.domains;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order {
    private final Date orderDate;
    private final List<Product> products;
    private BigDecimal totalPrice;

    public Order(Date orderDate, List<Product> products) {
        this.totalPrice = BigDecimal.ZERO;
        this.orderDate = orderDate;
        this.products = new ArrayList<>();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public boolean addProduct(Product product) {
        if (product == null || product.getPrice() == null) return false;
        this.totalPrice = totalPrice.add(product.getPrice());
        products.add(product);
        return true;
    }

    public void addProducts(Product ... productsToAdd) {
        for (Product p : productsToAdd) addProduct(p);
    }

    public boolean removeProduct(Product product) {
        if (product == null || product.getPrice() == null || !products.contains(product)) return false;
        this.totalPrice = totalPrice.subtract(product.getPrice());
        products.remove(product);
        return true;
    }

    public void removeProducts(Product ... productsToRemove) {
        for (Product p : productsToRemove) removeProduct(p);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
