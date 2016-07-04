package br.com.wmoreira.domains.mapper;

import br.com.wmoreira.domains.Product;
import br.com.wmoreira.domains.dto.ProductDTO;

public class ProductMapper implements Mapper<ProductDTO, Product> {

    @Override
    public Product mapFromLeftObject(ProductDTO productDTO) {
        return new Product(productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getCategory());
    }

    @Override
    public ProductDTO mapFromRightObject(Product product) {
        return new ProductDTO(product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory());
    }
}
