package com.wmoreira.engine.component;

import br.com.wmoreira.domains.Order;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderComponent {

    private KieSession session;

    @Autowired
    public OrderComponent(KieSession session) {
        this.session = session;
    }

    public Order[] priceOrders(Order[] orders) {
        for (Order order : orders) session.insert(order);
        session.fireAllRules();
        return orders;
    }
}
