package com.app.cars.repository;

import com.app.cars.model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandsRespository extends JpaRepository<Brands, Integer> {
}
