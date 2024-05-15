package com.app.cars.service;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Brands;
import com.app.cars.model.Cars;
import com.app.cars.repository.BrandsRespository;
import com.app.cars.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarsService {
    private final CarsRepository carsRepository;
    private final BrandsRespository brandsRespository;

    public ResponseEntity<ApiResponde<Cars>> createCars(Cars cars) {
        try {
            Integer brandId = cars.getBrands().getBrand_id();

            Optional<Brands> existingBrandsOpcional = brandsRespository.findById(brandId);
            if (existingBrandsOpcional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponde<>(null, "error", "Marca no encontrada con el id " + brandId));
            }
            Brands existingBrands = existingBrandsOpcional.get();

            cars.setBrands(existingBrands);

            Cars newCars = carsRepository.save(cars);

            ApiResponde<Cars> responde = new ApiResponde<>(newCars, "success", "Auto creado con éxito");

            return ResponseEntity.status(HttpStatus.CREATED).body(responde);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponde<>(null, "error", "error al crear el carro " + e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponde<Cars>> getCarsById(@PathVariable Integer id) {
        try {
            Optional<Cars> optionalCars = carsRepository.findById(id);
            if (optionalCars.isPresent()) {
                Cars cars = optionalCars.get();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponde<>(cars, "success", "Auto encontrado con éxito"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponde<>(null, "error", "Auto no encontrado con el ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponde<>(null, "error", "Error al obtener el auto " + e.getMessage()));
        }
    }

    public Page<Cars> getAllCars (Pageable pageable){
        return carsRepository.findAll(pageable);
    }


}
