package br.com.wmoreira.domains;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient BigDecimal basePrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer discountPercentage;

    public Order(Date orderDate, List<Product> products) {
        this.totalPrice = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.discountPercentage = 0;
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
        this.basePrice = totalPrice;
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
        this.basePrice = totalPrice;
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
                .multiply(totalPrice)
                .setScale(2, BigDecimal.ROUND_CEILING);
        totalPrice = basePrice.subtract(discount);
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

    public boolean hasProductsWithCategory(String ... categories) {
        List<String> existingCategories = new ArrayList<>();
        boolean matched = false;
        for (Product p : getProducts()) existingCategories.add(p.getCategory());
        for (String s : categories) {
            if (!matched) {
                matched = !matched && existingCategories.contains(s);
            } else {
                matched = matched && existingCategories.contains(s);
            }
        }
        return matched;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}
