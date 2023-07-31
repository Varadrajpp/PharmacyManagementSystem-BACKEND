package com.casestudy.InventoryService.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.InventoryService.entity.AvailableStock;
import com.casestudy.InventoryService.entity.SoldStock;
import com.casestudy.InventoryService.exception.BatchNotFoundException;
import com.casestudy.InventoryService.exception.DrugNotFoundException;
import com.casestudy.InventoryService.exception.StockNotFoundException;
import com.casestudy.InventoryService.exception.UnableToAddStockException;
import com.casestudy.InventoryService.exception.UnableToDeleteStockException;
import com.casestudy.InventoryService.exception.UnableToUpdateStockException;
import com.casestudy.InventoryService.repository.AvailableStockRepository;
import com.casestudy.InventoryService.repository.CartMicroserviceClient;
import com.casestudy.InventoryService.repository.SoldStockRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/inventory/available-stock")
@CrossOrigin(origins = "http://localhost:4200")

public class AvailableStockController {
	
	private static final Logger logger = LoggerFactory.getLogger(AvailableStockController.class);
	
    @Autowired
    private AvailableStockRepository availableStockRepository;
    
    @Autowired
    private SoldStockRepository soldStockRepository;

    @Autowired(required=false)
    private CartMicroserviceClient cartMicroserviceClient;

    @PostMapping
    public List<AvailableStock> addAvailableStock(@RequestBody List<AvailableStock> availableStockList) {
        try {
            return availableStockRepository.saveAll(availableStockList);
        } catch (Exception e) {
            logger.error("Error occurred while adding available stock: {}", e.getMessage());
            throw new UnableToAddStockException("Unable to add stock: " + e.getMessage());
        }
    }

    @GetMapping
    public List<AvailableStock> getAllAvailableStock() {
        try {
            return availableStockRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving available stock: {}", e.getMessage());
            throw e; 
        }
    }

    @GetMapping("/{id}")
    public AvailableStock getAvailableStockById(@PathVariable Long id) {
        try {
            return availableStockRepository.findById(id).orElseThrow(() -> new StockNotFoundException("Stock not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error occurred while retrieving available stock by ID: {}", e.getMessage());
            throw new StockNotFoundException("Error occurred while retrieving available stock by ID");
        }
    }
    
    @GetMapping("/searchbyBatchid/{batchid}")
    public List<AvailableStock> getAvailableStockByBatchId(@PathVariable String batchid) {
        try {
            AvailableStock stock = availableStockRepository.findByBatchId(batchid);
            if (stock == null) {
                throw new BatchNotFoundException("Batch not found with ID: " + batchid);
            }
            return availableStockRepository.findAllByBatchId(batchid);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving available stock by Batch ID: {}", e.getMessage());
            throw new BatchNotFoundException("Error occurred while retrieving available stock by Batch ID");
        }
    }

    

    @DeleteMapping("/{did}")
    public void deleteAvailableStockById(@PathVariable Long did) {
        try {
            availableStockRepository.deleteById(did);
        } catch (Exception e) {
            logger.error("Error occurred while deleting available stock by ID: {}", e.getMessage());
            throw new UnableToDeleteStockException("Unable to delete stock with ID: " + did);
        }
    }


    @DeleteMapping("/delete-by-drug-name/{drugName}")
    @Transactional
    public void deleteAvailableStockByDrugName(@PathVariable String drugName) {
        try {
            availableStockRepository.deleteByDrugName(drugName);
        } catch (Exception e) {
            logger.error("Error occurred while deleting available stock by drug name: {}", e.getMessage());
            throw new DrugNotFoundException("Unable to delete stock for drug: " + drugName);
        }
    }


    @DeleteMapping("/delete-by-batch-id/{batchId}")
    @Transactional
    public void deleteAvailableStockByBatchId(@PathVariable String batchId) {
        try {
            availableStockRepository.deleteByBatchId(batchId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting available stock by batch ID: {}", e.getMessage());
            throw e; 
        }
    }
    
    @GetMapping("/search-by-drug-name/{drugName}")
    public List<AvailableStock> searchAvailableStockByDrugName(@PathVariable String drugName) {
        try {
            return availableStockRepository.findByDrugName(drugName);
        } catch (Exception e) {
            logger.error("Error occurred while searching available stock by drug name: {}", e.getMessage());
            throw e; 
        }
    }

    @PutMapping("/update/{id}")
    public AvailableStock updateAvailableStock(@PathVariable Long id, @RequestBody AvailableStock updatedStock) {
        try {
            AvailableStock stock = availableStockRepository.findById(id).orElse(null);
            if (stock != null) {
                stock.setBatchId(updatedStock.getBatchId());
                stock.setDrugName(updatedStock.getDrugName());
                stock.setSupplierEmail(updatedStock.getSupplierEmail());
                stock.setQuantity(updatedStock.getQuantity());
                stock.setExpiryDate(updatedStock.getExpiryDate());
                stock.setPrice(updatedStock.getPrice());
                return availableStockRepository.save(stock);
            }
            throw new UnableToUpdateStockException("Unable to update stock with ID: " + id);
        } catch (Exception e) {
            logger.error("Error occurred while updating available stock: {}", e.getMessage());
            throw new UnableToUpdateStockException("Error occurred while updating available stock");
        }
    }

    
    @DeleteMapping("/delete-by-batchid/{batchId}")
    @Transactional
    public void deleteAvailableStockandUpdate(@PathVariable String batchId) {
        AvailableStock availableStock = availableStockRepository.findByBatchId(batchId);
        if (availableStock != null) {
            SoldStock soldStock = new SoldStock();
            soldStock.setBatchId(availableStock.getBatchId());
            soldStock.setDrugName(availableStock.getDrugName());
            soldStock.setSupplierEmail(availableStock.getSupplierEmail());
            soldStock.setQuantity(availableStock.getQuantity());
            soldStock.setExpiryDate(availableStock.getExpiryDate());
            soldStock.setPrice(availableStock.getPrice());
            soldStock.setTotalPrice(availableStock.getQuantity() * availableStock.getPrice());

            soldStockRepository.save(soldStock);

            availableStockRepository.delete(availableStock);

           
            cartMicroserviceClient.removeItemsByBatchId(batchId);
        }
    }
    

}
