package com.app.cars.controller;

import com.app.cars.model.ApiResponde;
import com.app.cars.model.Cars;
import com.app.cars.repository.CarsRepository;
import com.app.cars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarsController {
    private final CarsService carsService;
    private final CarsRepository carsRepository;
    private static final Logger logger = Logger.getLogger(CarsController.class.getName());
    @PostMapping
    public ResponseEntity<ApiResponde<Cars>> createCars(@RequestBody Cars cars){
        return carsService.createCars(cars);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponde<Cars>>getCarsById(@PathVariable Integer id){
        return carsService.getCarsById(id);
    }
    @GetMapping
    public ResponseEntity<ApiResponde<Page<Cars>>>getAllCars(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        logger.info("Llamada al endpoint GET/ recibida.");
        Page<Cars>carsPage = carsService.getAllCars(PageRequest.of(page, size));
        if (carsPage != null && carsPage.hasContent()){
            ApiResponde<Page<Cars>> responde = new ApiResponde<>(carsPage, "success", "Autos encontrados con éxito");
            return  ResponseEntity.ok(responde);
        }else {
            ApiResponde<Page<Cars>> responde = new ApiResponde<>(null, "error", "Se no encontraron autos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responde);
        }
    }
}
