package com.vehicle_service.appointment_service.repository;


import com.vehicle_service.appointment_service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
