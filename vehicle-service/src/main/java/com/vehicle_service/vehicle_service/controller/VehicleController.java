package com.vehicle_service.vehicle_service.controller;



import com.vehicle_service.vehicle_service.entity.Vehicle;
import com.vehicle_service.vehicle_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

//    READ ALL
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

//    READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

//    UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle){
        return ResponseEntity.ok(vehicleService.updateVehicle(id, updatedVehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicles(id);
        return ResponseEntity.noContent().build();
    }



}
