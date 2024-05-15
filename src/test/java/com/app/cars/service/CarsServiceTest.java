package com.app.cars.service;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Brands;
import com.app.cars.model.Cars;
import com.app.cars.repository.BrandsRespository;
import com.app.cars.repository.CarsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarsServiceTest {
    @Mock
    private CarsRepository carsRepository;
    @Mock
    private BrandsRespository brandsRespository;
    @InjectMocks
    private CarsService carsService;
    @Test
    void createCars() {
        Cars imputCars = new Cars();
        Brands brands = new Brands();
        brands.setBrand_id(1);
        imputCars.setBrands(brands);

        when(brandsRespository.findById(1)).thenReturn(Optional.of(brands));
        when(carsRepository.save(imputCars)).thenReturn(imputCars);

        ResponseEntity<ApiResponde<Cars>> responseEntity = carsService.createCars(imputCars);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Auto creado con éxito",responseEntity.getBody().getMessage());
        assertEquals(imputCars, responseEntity.getBody().getData());
    }
    @Test
    void getCarsById(){
        Cars expectedCars = new Cars();
        when(carsRepository.findById(1)).thenReturn(Optional.of(expectedCars));

        ResponseEntity<ApiResponde<Cars>> responseEntity = carsService.getCarsById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Auto encontrado con éxito", responseEntity.getBody().getMessage());
        assertEquals(expectedCars, responseEntity.getBody().getData());
    }
    @Test
    void getAllCars (){
        Pageable pageable = Pageable.unpaged();
        List<Cars> carsList = new ArrayList<>();
        Page<Cars> expectedPage = new PageImpl<>(carsList);
        when(carsRepository.findAll(pageable)).thenReturn(expectedPage);
        Page<Cars> resulPage = carsService. getAllCars(pageable);
        assertEquals(expectedPage, resulPage);
    }

}