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

import com.casestudy.OrdersService.entity.CombinedOrder;
import com.casestudy.OrdersService.repo.CombinedOrderRepository;

@RestController
@RequestMapping("/orders/combined")
public class CombinedOrderController {
    private final CombinedOrderRepository combinedOrderRepository;

    @Autowired
    public CombinedOrderController(CombinedOrderRepository combinedOrderRepository) {
        this.combinedOrderRepository = combinedOrderRepository;
    }

    @PostMapping
    public CombinedOrder createCombinedOrder(@RequestBody CombinedOrder combinedOrder) {
        return combinedOrderRepository.save(combinedOrder);
    }
   

    @GetMapping
    public List<CombinedOrder> getAllCombinedOrders() {
        return combinedOrderRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCombinedOrder(@PathVariable Long id) {
        combinedOrderRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public List<CombinedOrder> searchbyId(@PathVariable Long id) {
    	return combinedOrderRepository.findAllById(id);
    }
}
