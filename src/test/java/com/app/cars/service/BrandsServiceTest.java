package com.app.cars.service;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Brands;
import com.app.cars.repository.BrandsRespository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandsServiceTest {
    @Mock
    private BrandsRespository brandsRespository;
    @InjectMocks
    private BrandsService brandsService;
    @Test
    void createBrands() {
        BrandsRespository brandsRespository = mock(BrandsRespository.class);
        BrandsService brandsService = new BrandsService(brandsRespository);
        Brands mockBrands = new Brands();
        Brands savedBrands = new Brands();

        when(brandsRespository.save(any())).thenReturn(savedBrands);

        ResponseEntity<ApiResponde<Brands>> responseEntity = brandsService.createBrands(mockBrands);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Marca creada con éxito", responseEntity.getBody().getMessage());
        assertEquals(savedBrands,responseEntity.getBody().getData());
    }
}