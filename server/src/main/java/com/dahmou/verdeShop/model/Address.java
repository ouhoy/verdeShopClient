package com.dahmou.verdeShop.model;

import java.io.Serializable;

public record Address(

        String firstName,
        String lastName,
        String email,
        String street,
        String country,
        String city,
        String state,
        String zip
) implements Serializable {
    // ... (constructor and methods)
}