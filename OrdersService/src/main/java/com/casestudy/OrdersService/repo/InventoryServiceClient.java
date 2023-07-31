package com.casestudy.OrdersService.repo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.casestudy.OrdersService.entity.AvailableStock;





@FeignClient(name="inventory-service",url = "http://localhost:8081")
@Service
public interface InventoryServiceClient {

    @DeleteMapping("/inventory/available-stock/{id}")
    void deleteAvailableStockById(@PathVariable Long id);

    // Define other methods for interacting with the Inventory microservice
    @GetMapping("/inventory/available-stock/{id}")
    AvailableStock getAvailableStockById(@PathVariable Long id); 
    
    @GetMapping("/inventory/available-stock/searchbyBatchid/{batchid}")
    public AvailableStock getAvailableStockByBatchId(@PathVariable String batchid) ;
    
   
}
