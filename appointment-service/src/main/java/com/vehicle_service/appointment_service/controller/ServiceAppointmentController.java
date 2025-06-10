package com.vehicle_service.appointment_service.controller;



import com.vehicle_service.appointment_service.entity.Service;
import com.vehicle_service.appointment_service.entity.ServiceAppointments;
import com.vehicle_service.appointment_service.repository.ServiceRepository;
import com.vehicle_service.appointment_service.service.ServiceAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class ServiceAppointmentController {

    @Autowired
    private ServiceAppointmentService serviceAppointmentService;

    @Autowired
    private ServiceRepository serviceRepository;

    @PutMapping("/{appointmentId}/assign-services")
    public ResponseEntity<ServiceAppointments> assignServicesToAppointments(
            @PathVariable Long appointmentId,
            @RequestBody List<Long> serviceIds
    ){
        Optional<ServiceAppointments> optionalAppointment = serviceAppointmentService.getAppointmentsById(appointmentId);
        if(!optionalAppointment.isPresent()){
            return ResponseEntity.notFound().build();
        }

        List<Service> services = serviceRepository.findAllById(serviceIds);
        ServiceAppointments appointment = optionalAppointment.get();
        appointment.setServices(services);
        ServiceAppointments updated = serviceAppointmentService.addAppointments(appointment);

        return ResponseEntity.ok(updated);
    }

    @PostMapping
    public ServiceAppointments addAppointments(@RequestBody ServiceAppointments appointments){
        return serviceAppointmentService.addAppointments(appointments);
    }

    @GetMapping
    public List<ServiceAppointments> getAllAppointments(){
        return serviceAppointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public  ServiceAppointments getAppointmentById(@PathVariable Long id){
        return serviceAppointmentService.getAppointmentsById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ServiceAppointments updateAppointments(@PathVariable Long id, @RequestBody ServiceAppointments appointment){
        return serviceAppointmentService.updateAppointments(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        serviceAppointmentService.deleteAppointments(id);
    }

}
