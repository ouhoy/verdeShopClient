package com.dahmou.verdeShop.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public record Product(
        @Id
        Integer id,
        @NotBlank
        String name,
        Integer price,
        String description,
        String[] highlights,
        String details,
        String thumbnail,
        String[] imageSrc,
        String imageAlt,
        String[] colors,
        Gender gender,
        String[] sizes,
        String[] options,
        Type type



)  implements Serializable {

}