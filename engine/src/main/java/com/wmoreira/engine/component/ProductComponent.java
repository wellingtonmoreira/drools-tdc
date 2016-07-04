package com.wmoreira.engine.component;

import br.com.wmoreira.domains.Product;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductComponent {

    private KieSession session;

    @Autowired
    public ProductComponent(KieSession session) {
        this.session = session;
    }

    public Product[] priceProducts(Product[] products) {
        for (Product product : products) session.insert(product);
        session.fireAllRules();
        return products;
    }
}
