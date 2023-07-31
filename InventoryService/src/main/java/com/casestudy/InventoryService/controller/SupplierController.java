package com.casestudy.InventoryService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.InventoryService.entity.AvailableStock;
import com.casestudy.InventoryService.entity.SupplierDTO;
import com.casestudy.InventoryService.repository.AvailableStockRepository;

import jakarta.transaction.Transactional;



@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {

	 @Autowired
	    private AvailableStockRepository availableStockRepository;
	
	 @PutMapping("/edit-supplier/{supplierEmail}")
	 public ResponseEntity<SupplierDTO> editSupplier(@PathVariable String supplierEmail, @RequestBody SupplierDTO updatedSupplierDTO) {
	     AvailableStock existingSupplier = availableStockRepository.findBySupplierEmail(supplierEmail);

	     if (existingSupplier != null) {
	         existingSupplier.setSupplierEmail(updatedSupplierDTO.getSupplierEmail());
	         existingSupplier.setDrugName(updatedSupplierDTO.getDrugName());
	         availableStockRepository.save(existingSupplier);

	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }


	 @DeleteMapping("/delete-supplier")
	 @Transactional
	 public void deleteSupplier(@RequestParam String drugName) {
	     availableStockRepository.deleteByDrugName(drugName);
	     
	 }
    
    @GetMapping("/view-suppliers")
    public List<SupplierDTO> viewSuppliers() {
        List<AvailableStock> availableStockList = availableStockRepository.findAll();
        List<SupplierDTO> supplierList = new ArrayList<>();

        for (AvailableStock stock : availableStockList) {
            SupplierDTO supplierDTO = new SupplierDTO(stock.getSupplierEmail(), stock.getDrugName());
            supplierList.add(supplierDTO);
        }

        return supplierList;
    }
}
