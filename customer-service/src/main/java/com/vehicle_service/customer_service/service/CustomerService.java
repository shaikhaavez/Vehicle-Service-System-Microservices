package com.vehicle_service.customer_service.service;


//import com.example.servicemgmt.VehicleServiceManagmentApp.entity.Customer;
//import com.example.servicemgmt.VehicleServiceManagmentApp.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.vehicle_service.customer_service.entity.Customer;
import com.vehicle_service.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerbyId(Long id){
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer){
        return customerRepository.findById(id).map(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(customer);
        }).orElse(null);

    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

}
