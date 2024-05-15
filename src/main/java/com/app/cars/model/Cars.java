package com.app.cars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Cars {
    @Id
    @GeneratedValue
    private Integer car_id;
    @Size(max = 30, message = "EL modelo no puede tener mas de 30 caracteres")
    private String model;
    @Size(max = 100, message = "la descripcion no puede tener mas de 100 caracteres")
    private String description;
    private Double price;
    private Integer mileage;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brands;
}
