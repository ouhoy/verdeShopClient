package com.dahmou.verdeShop.controller;


import com.dahmou.verdeShop.model.User;
import com.dahmou.verdeShop.repository.UserCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/users/")
@CrossOrigin
public class UserController {

    private final UserCollectionRepository repository;


    public UserController(UserCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<User> findAll() {
        return repository.getAllUsers();
    }


//    @GetMapping("/{id}")
//    public User findById(@PathVariable int id) {
//        User user = repository.getUser(id);
//        if (user == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return user;
//    }

    @GetMapping("/{email}")
    public User findByEmail(@PathVariable String email) {
        User user = repository.getUserByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody User user) {
        repository.createUser(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody User user) {
        repository.updateUser(id, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{id}")
    public void delete(@RequestBody @PathVariable int id) {
        repository.deleteUser(id);
    }
}
