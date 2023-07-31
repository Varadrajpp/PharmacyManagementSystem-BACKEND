package com.casestudy.OrdersService.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String batchId;
    private String drugName;
    private double price;
    private int quantity;
    private double totalPrice;
    private String expiryDate;
    private String doctorName;
    private String doctorContactNumber;
    private String doctorEmail;
    private String supplierEmail;
    
    
    
    
    
    
    
    
    
	public Cart() {
		super();
	}
	public Cart(Long id, String batchId, String drugName, double price, int quantity, double totalPrice,
			String expiryDate, String doctorName, String doctorContactNumber, String doctorEmail,
			String supplierEmail) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.drugName = drugName;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.expiryDate = expiryDate;
		this.doctorName = doctorName;
		this.doctorContactNumber = doctorContactNumber;
		this.doctorEmail = doctorEmail;
		this.supplierEmail = supplierEmail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorContactNumber() {
		return doctorContactNumber;
	}
	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
       
    
    
    
  
	    
    
}
