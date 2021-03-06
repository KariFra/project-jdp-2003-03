package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.ProductGroup;
import com.kodilla.ecommercee.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ProductGroupService productGroupService;

    public Product mapToProduct(final ProductDto productDto) {
        ProductGroup productGroup = productGroupService.getGroupOrException(productDto.getGroupId());
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getBrand(),
                productDto.getModel(),
                productDto.getYear(),
                productDto.getOrigin(),
                productDto.getDescription(),
                productGroup);
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getModel(),
                product.getYear(),
                product.getOrigin(),
                product.getDescription(),
                product.getProductGroup().getId());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
