package com.vehicle_service.appointment_service.service;

//import com.example.servicemgmt.VehicleServiceManagmentApp.entity.ServiceAppointments;
//import com.example.servicemgmt.VehicleServiceManagmentApp.repository.ServiceAppointmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.vehicle_service.appointment_service.entity.ServiceAppointments;
import com.vehicle_service.appointment_service.repository.ServiceAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAppointmentService {

    @Autowired
    private ServiceAppointmentRepository serviceAppointmentRepository;

    public ServiceAppointments addAppointments(ServiceAppointments appointments){
        return serviceAppointmentRepository.save(appointments);
    }

    public List<ServiceAppointments> getAllAppointments(){
        return serviceAppointmentRepository.findAll();
    }

    public Optional<ServiceAppointments> getAppointmentsById(Long id){
        return serviceAppointmentRepository.findById(id);
    }

    public ServiceAppointments updateAppointments(Long id, ServiceAppointments updatedAppointment){
        return serviceAppointmentRepository.findById(id).map(existing -> {
            existing.setAppointmentDate(updatedAppointment.getAppointmentDate());
            existing.setStatus(updatedAppointment.getStatus());
            existing.setVehicle(updatedAppointment.getVehicle());
            existing.setServices(updatedAppointment.getServices());
            return serviceAppointmentRepository.save(existing);
        }).orElse(null);
    }

    public void deleteAppointments(Long id){
        serviceAppointmentRepository.deleteById(id);
    }


}
