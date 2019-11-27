package com.bt.au.shoppingcart.controllers;

import com.bt.au.shoppingcart.model.Product;
import com.bt.au.shoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;


    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product save(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public List<Product> list(@PathParam("page") Pageable page) {
        return productRepository.findAll(page).getContent();
    }

}
