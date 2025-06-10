package com.vehicle_service.customer_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_id;

    private String service_name;

    private BigDecimal price;

    @Column(name = "duration_min")
    private int durationMin;

    @ManyToMany(mappedBy = "services")
    @JsonBackReference
    private List<ServiceAppointments> appointments;

}
