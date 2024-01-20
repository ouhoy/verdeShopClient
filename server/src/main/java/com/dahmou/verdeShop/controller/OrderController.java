package com.dahmou.verdeShop.controller;


import com.dahmou.verdeShop.model.Order;
import com.dahmou.verdeShop.model.Product;
import com.dahmou.verdeShop.repository.OrderCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    @GetMapping("/{id}")
    public Order findById(@PathVariable int id){
        Order order = repository.getOrder(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Order order) {
        repository.createOrder(order);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{id}")
    public void delete(@RequestBody @PathVariable int id) {
        repository.deleteOrder(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update( @PathVariable int id, @RequestBody Order order) {
        repository.updateOrder(id,order);
    }

}
