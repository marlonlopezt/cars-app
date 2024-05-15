package com.app.cars.service;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Brands;
import com.app.cars.repository.BrandsRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandsService {
    private final BrandsRespository brandsRespository;

    public ResponseEntity<ApiResponde<Brands>> createBrands(Brands brands){
        try{
            Brands newBrands = brandsRespository.save(brands);
            ApiResponde<Brands> responde = new ApiResponde<>(newBrands,"success","Marca creada con éxito");
            return ResponseEntity.status(HttpStatus.CREATED).body(responde);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponde<>(null, "error","error al crear la marca" + e.getMessage()));
        }
    }
}
