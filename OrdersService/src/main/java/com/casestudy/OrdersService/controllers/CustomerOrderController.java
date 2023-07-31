package com.casestudy.OrdersService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.OrdersService.entity.CustomerOrder;
import com.casestudy.OrdersService.repo.CustomerOrderRepository;

@RestController
@RequestMapping("/orders/customer")
public class CustomerOrderController {
    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderController(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @PostMapping
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return customerOrderRepository.save(customerOrder);
    }

    @GetMapping
    public List<CustomerOrder> getAllCustomerOrders() {
        return customerOrderRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerOrder(@PathVariable Long id) {
        customerOrderRepository.deleteById(id);
    }
}
