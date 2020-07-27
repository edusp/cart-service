package com.bt.au.shoppingcart.services;

import com.bt.au.shoppingcart.model.Category;
import com.bt.au.shoppingcart.model.Product;
import com.bt.au.shoppingcart.model.dtos.ProductDto;
import com.bt.au.shoppingcart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public Product save(ProductDto productDto) {
        Product product = Product.fromDto(productDto);
        return repository.save(product);
    }

    public List<ProductDto> listPaginated(Pageable pageable) {
        Page<Product> paginated = repository.findAll(pageable);
        return asResponse(paginated.getContent());
    }

    public List<ProductDto> listAllByCategory(Category category) {
        List<Product> products = repository.findByCategory(category);
        return asResponse(products);
    }

    private List<ProductDto> asResponse(List<Product> products) {
        List<ProductDto> productsResponse = new ArrayList<>();
        products.forEach(product -> {
            productsResponse.add(Product.toDto(product));
        });
        return productsResponse;

    }


}
