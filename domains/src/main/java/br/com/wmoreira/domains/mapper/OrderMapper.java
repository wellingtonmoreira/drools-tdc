package br.com.wmoreira.domains.mapper;

import br.com.wmoreira.domains.Order;
import br.com.wmoreira.domains.Product;
import br.com.wmoreira.domains.dto.OrderDTO;
import br.com.wmoreira.domains.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements Mapper<OrderDTO, Order> {

    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Order mapFromLeftObject(OrderDTO orderDTO) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO p : orderDTO.getProducts()) products.add(productMapper.mapFromLeftObject(p));
        return new Order(orderDTO.getOrderDate(), products);
    }

    @Override
    public OrderDTO mapFromRightObject(Order order) {
        List<ProductDTO> products = new ArrayList<>();
        for (Product p : order.getProducts()) products.add(productMapper.mapFromRightObject(p));
        return new OrderDTO(order.getOrderDate(), products.toArray(new ProductDTO[products.size()]), order.getTotalPrice());
    }
}
