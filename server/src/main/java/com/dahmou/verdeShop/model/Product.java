package com.dahmou.verdeShop.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record Product(
        @Id
        Integer id,
        @NotBlank
        String name,
        Integer price,
        String description,
        String[] highlights,
        String details,
        String[] imageSrc,
        String imageAlt,
        String[] colors,
        Gender gender,
        Size[] sizes,
        String[] options,
        Type type



) {
}
