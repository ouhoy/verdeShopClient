package com.dahmou.verdeShop.controller;

import com.dahmou.verdeShop.model.Product;
import com.dahmou.verdeShop.repository.ProductCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/products/")
@CrossOrigin
public class ProductController {

    private final ProductCollectionRepository repository;


    public ProductController(ProductCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Product> findAll(){
        return repository.getAllProducts();
    }

    @GetMapping("/{id}")
     public Product findById(@PathVariable int id){
        Product product = repository.getProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return product;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Product product) {
        repository.createProduct(product);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update( @PathVariable int id, @RequestBody  Product product) {
        repository.updateProduct(id,product);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{id}")
    public void delete(@RequestBody @PathVariable int id) {
        repository.deleteProduct(id);
    }

}
