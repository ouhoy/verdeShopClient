package com.dahmou.verdeShop.controller;

import com.dahmou.verdeShop.model.Gender;
import com.dahmou.verdeShop.model.Product;
import com.dahmou.verdeShop.model.Size;
import com.dahmou.verdeShop.model.Type;
import com.dahmou.verdeShop.repository.ProductCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProductController {

    private final ProductCollectionRepository repository;


    public ProductController(ProductCollectionRepository repository) {
        this.repository = repository;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Product product) {
        repository.createProduct(product);
    }

}
