package com.dahmou.verdeShop.controller;


import com.dahmou.verdeShop.model.Order;
import com.dahmou.verdeShop.repository.OrderCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders/")
@CrossOrigin
public class OrderController {

    private final OrderCollectionRepository repository;


    public OrderController(OrderCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Order> findAll(){
        return repository.getAllOrders();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Order order) {
        repository.createOrder(order);
    }
}
