package com.vehicle_service.vehicle_service.service;


import com.vehicle_service.vehicle_service.entity.Vehicle;
import com.vehicle_service.vehicle_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle){
        Vehicle vehicle = getVehicleById(id);
        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setCompany(updatedVehicle.getCompany());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setYear(updatedVehicle.getYear());
        vehicle.setCustomer(updatedVehicle.getCustomer());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicles(Long id){
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }



}


//controller  ---> service  ---> Repository
