package com.wmoreira.engine.presentation.service;

import br.com.wmoreira.domains.Product;
import br.com.wmoreira.domains.dto.ProductDTO;
import br.com.wmoreira.domains.mapper.ProductMapper;
import com.wmoreira.engine.component.ProductComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {

    private ProductMapper productMapper;
    private ProductComponent productComponent;

    @Autowired
    public ProductService(ProductMapper productMapper, ProductComponent productComponent) {
        this.productMapper = productMapper;
        this.productComponent = productComponent;
    }

    @RequestMapping(value = "/products/price",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public ResponseEntity<ProductDTO[]> price(@RequestBody ProductDTO[] productDTOs) {
        Product[] products = productMapper.mapManyFromLeftObject(productDTOs);
        return ResponseEntity.ok().body(productMapper.mapManyFromRightObject(productComponent.priceProducts(products)));
    }
}
