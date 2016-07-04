package br.com.wmoreira.domains.mapper;

import br.com.wmoreira.domains.Order;
import br.com.wmoreira.domains.Product;
import br.com.wmoreira.domains.dto.OrderDTO;
import br.com.wmoreira.domains.dto.ProductDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements Mapper<OrderDTO, Order> {

    private final ProductMapper productMapper;
    private final SimpleDateFormat sdf;

    public OrderMapper(ProductMapper productMapper, SimpleDateFormat sdf) {
        this.productMapper = productMapper;
        this.sdf = sdf;
    }

    @Override
    public Order mapFromLeftObject(OrderDTO orderDTO) {
        try {
            List<Product> products = new ArrayList<>();
            for (ProductDTO p : orderDTO.getProducts()) products.add(productMapper.mapFromLeftObject(p));
            return new Order(sdf.parse(orderDTO.getOrderDate()), products);
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public OrderDTO mapFromRightObject(Order order) {
        try {
            List<ProductDTO> products = new ArrayList<>();
            for (Product p : order.getProducts()) products.add(productMapper.mapFromRightObject(p));
            return new OrderDTO(sdf.format(order.getOrderDate()), products.toArray(new ProductDTO[products.size()]), order.getTotalPrice());
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Order[] mapManyFromLeftObject(OrderDTO[] orderDTOs) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO dto : orderDTOs) orders.add(mapFromLeftObject(dto));
        return orders.toArray(new Order[orders.size()]);
    }

    @Override
    public OrderDTO[] mapManyFromRightObject(Order[] orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) orderDTOs.add(mapFromRightObject(order));
        return orderDTOs.toArray(new OrderDTO[orderDTOs.size()]);
    }
}
