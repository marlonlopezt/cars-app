package com.app.cars.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data

public class Brands {
    @Id
    @GeneratedValue
    private Integer brand_id;
    @Size(max=20, message = " El nombre no puede tener mas de 20 caracteres")
    private String name;

}
