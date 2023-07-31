package com.casestudy.OrdersService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.OrdersService.entity.Cart;

public interface Cartrepository extends JpaRepository<Cart, Long>{

	List<Cart> findByBatchId(String batchId);

	void deleteByBatchId(String batchId);



}
