package com.vehicle_service.appointment_service.repository;


import com.vehicle_service.appointment_service.entity.ServiceAppointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAppointmentRepository extends JpaRepository<ServiceAppointments, Long> {
}
