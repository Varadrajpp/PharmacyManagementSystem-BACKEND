package com.casestudy.InventoryService.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.InventoryService.entity.SoldStock;
import com.casestudy.InventoryService.repository.SoldStockRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/inventory/sold-stock")
@CrossOrigin(origins = "http://localhost:4200")
public class SoldStockController {
    @Autowired
    private SoldStockRepository soldStockRepository;

    @PostMapping
    public List<SoldStock> addSoldStock(@RequestBody List<SoldStock> soldStockList) {
        for (SoldStock soldStock : soldStockList) {
            int quantity = soldStock.getQuantity();
            double price = soldStock.getPrice();
            double totalPrice = quantity * price;
            soldStock.setTotalPrice(totalPrice);
        }

        return soldStockRepository.saveAll(soldStockList);
    }

    @GetMapping
    public List<SoldStock> getAllSoldStock() {
        return soldStockRepository.findAll();
    }

    @GetMapping("/{id}")
    public SoldStock getSoldStockById(@PathVariable Long id) {
        return soldStockRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteSoldStockById(@PathVariable Long id) {
        soldStockRepository.deleteById(id);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateSalesReport() throws IOException {
        List<SoldStock> soldStockList = soldStockRepository.findAll();
        
        // Create a new document
        Document document = new Document();
        
        try {
            // Create a ByteArrayOutputStream to hold the generated PDF content
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            
            // Create a PdfWriter instance to write the document to the output stream
            PdfWriter.getInstance(document, outputStream);
            
            // Open the document
            document.open();
            
            // Add a title to the document
            document.add(new Paragraph("                                                                   Sales Report                         "));
            document.add(new Paragraph("                                                                  "));
            // Add sold stock details to the document
            for (SoldStock soldStock : soldStockList) {
                document.add(new Paragraph("Batch ID: " + soldStock.getBatchId()));
              
                document.add(new Paragraph("Drug Name: " + soldStock.getDrugName()));
                document.add(new Paragraph("Supplier Email: " + soldStock.getSupplierEmail()));
                document.add(new Paragraph("Quantity: " + soldStock.getQuantity()));
                document.add(new Paragraph("Expiry Date: " + soldStock.getExpiryDate()));
                document.add(new Paragraph("Price: " + soldStock.getPrice()));
                document.add(new Paragraph("Total Price: " + soldStock.getTotalPrice()));
                document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            }								

            
            // Close the document
            document.close();
            
            // Set the appropriate headers for the HTTP response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "sales_report.pdf");
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (DocumentException e) {
            System.out.println("Error occurred while generating sales report: " + e.getMessage());
        }
        
        return ResponseEntity.badRequest().build();
    }

}
