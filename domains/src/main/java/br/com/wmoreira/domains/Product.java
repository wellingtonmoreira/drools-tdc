package br.com.wmoreira.domains;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String description;
    private String category;

    public Product() {}

    public Product(String name, BigDecimal price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProfit(int profitPercentage) {
        this.price = new BigDecimal(profitPercentage)
                        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP)
                        .add(BigDecimal.ONE)
                        .multiply(price)
                        .setScale(2, BigDecimal.ROUND_CEILING);
    }
}
