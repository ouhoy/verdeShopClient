package com.dahmou.verdeShop.model;

public record User(

        Integer id,
        String firstName,
        String lastName,
        String email,
        UserType userType
) {
}
