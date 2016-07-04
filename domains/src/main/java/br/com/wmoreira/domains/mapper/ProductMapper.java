package br.com.wmoreira.domains.mapper;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Product[] mapManyFromLeftObject(ProductDTO[] productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO dto : productDTOs) products.add(mapFromLeftObject(dto));
        return products.toArray(new Product[products.size()]);
    }

    @Override
    public ProductDTO[] mapManyFromRightObject(Product[] products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) productDTOs.add(mapFromRightObject(product));
        return productDTOs.toArray(new ProductDTO[productDTOs.size()]);
    }
}
