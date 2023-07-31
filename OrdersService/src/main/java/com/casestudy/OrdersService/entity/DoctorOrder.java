package com.casestudy.OrdersService.entity;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_orders")
public class DoctorOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;
    private String doctorContactNumber;
    private String doctorEmail;
    private String drugName;
    private double drugPrice;
    private int quantity;
    private double totalPrice;
    private LocalDate pickupDate;
    private String name;
	public DoctorOrder(Long id, String doctorName, String doctorContactNumber, String doctorEmail, String drugName,
			double drugPrice, int quantity, double totalPrice, LocalDate pickupDate) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.doctorContactNumber = doctorContactNumber;
		this.doctorEmail = doctorEmail;
		this.drugName = drugName;
		this.drugPrice = drugPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.pickupDate = pickupDate;
	}
	public DoctorOrder() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public double getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(double drugPrice) {
		this.drugPrice = drugPrice;
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
	public LocalDate getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}

	 public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
    
    
}
