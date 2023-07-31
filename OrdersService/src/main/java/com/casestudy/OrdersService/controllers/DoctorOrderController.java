package com.casestudy.OrdersService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.OrdersService.entity.DoctorOrder;
import com.casestudy.OrdersService.repo.DoctorOrderRepository;

@RestController
@RequestMapping("/orders/doctor")
public class DoctorOrderController {
    private final DoctorOrderRepository doctorOrderRepository;

    @Autowired
    public DoctorOrderController(DoctorOrderRepository doctorOrderRepository) {
        this.doctorOrderRepository = doctorOrderRepository;
    }

    @PostMapping
    public DoctorOrder createDoctorOrder(@RequestBody DoctorOrder doctorOrder) {
        return doctorOrderRepository.save(doctorOrder);
    }

    @GetMapping
    public List<DoctorOrder> getAllDoctorOrders() {
        return doctorOrderRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorOrder(@PathVariable Long id) {
        doctorOrderRepository.deleteById(id);
    }

    @GetMapping("/verify")
    public boolean verifyDoctorExists(@RequestParam String doctorName) {
        return doctorOrderRepository.existsByDoctorName(doctorName);
    }
}
