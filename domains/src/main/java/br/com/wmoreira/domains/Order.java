package br.com.wmoreira.domains;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order {
    private final Date orderDate;
    private final List<Product> products;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private int discountPercentage;

    public Order(Date orderDate, List<Product> products) {
        this.totalPrice = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.orderDate = orderDate;
        this.products = new ArrayList<>();
        addProducts(products.toArray(new Product[products.size()]));
        applyDiscount();
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
        applyDiscount();
        return true;
    }

    public void addProducts(Product ... productsToAdd) {
        for (Product p : productsToAdd) addProduct(p);
    }

    public boolean removeProduct(Product product) {
        if (product == null || product.getPrice() == null || !products.contains(product)) return false;
        this.totalPrice = totalPrice.subtract(product.getPrice());
        products.remove(product);
        applyDiscount();
        return true;
    }

    public void setDiscount(int discountPercentage) {
        this.discountPercentage = discountPercentage;
        applyDiscount();
    }

    void applyDiscount() {
        discount = new BigDecimal(discountPercentage).divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP)
                .add(BigDecimal.ONE)
                .multiply(totalPrice)
                .setScale(2, BigDecimal.ROUND_CEILING);
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
