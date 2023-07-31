package com.casestudy.InventoryService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.InventoryService.entity.DeliveredDrugs;
import com.casestudy.InventoryService.repository.DeliveredDrugsRepository;

@RestController
@RequestMapping("/inventory/delivered-stock")
public class DeliveredStockController {
    @Autowired
    private DeliveredDrugsRepository deliveredDrugsRepository;

    @PostMapping
    public ResponseEntity<List<DeliveredDrugs>> addDeliveredStock(@RequestBody List<DeliveredDrugs> deliveredDrugsList) {
        List<DeliveredDrugs> savedDeliveredDrugsList = deliveredDrugsRepository.saveAll(deliveredDrugsList);
        return new ResponseEntity<>(savedDeliveredDrugsList, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DeliveredDrugs> getAllDeliveredStock() {
        return deliveredDrugsRepository.findAll();
    }

    @GetMapping("/delivered")
    public List<DeliveredDrugs> getDeliveredDrugs() {
        return deliveredDrugsRepository.findByDeliveryStatus("Delivered");
    }

    @GetMapping("/{id}")
    public DeliveredDrugs getDeliveredStockById(@PathVariable Long id) {
        return deliveredDrugsRepository.findById(id).orElse(null);
    }
    @PostMapping("/{BATCHID}/mark-as-delivered")
    public ResponseEntity<?> markStockAsDelivered(@PathVariable("BATCHID") String batchId) {
        DeliveredDrugs deliveredStock = deliveredDrugsRepository.findByBatchId(batchId);
        if (deliveredStock != null) {
            deliveredStock.setDeliveryStatus("Delivered");
            deliveredDrugsRepository.save(deliveredStock);
            return ResponseEntity.ok().body("{\"message\": \"Stock marked as Delivered\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Stock not found\"}");
        }
    }

}
