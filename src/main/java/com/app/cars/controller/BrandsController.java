package com.app.cars.controller;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Brands;
import com.app.cars.service.BrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandsController {
    private final BrandsService brandsService;

    @PostMapping
    public ResponseEntity<ApiResponde<Brands>> createBrands (@RequestBody Brands brands){
        return  brandsService.createBrands(brands);
    }
}
