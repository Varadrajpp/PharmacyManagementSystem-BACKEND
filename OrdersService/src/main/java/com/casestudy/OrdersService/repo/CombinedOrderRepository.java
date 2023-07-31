package com.casestudy.OrdersService.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.OrdersService.entity.CombinedOrder;

public interface CombinedOrderRepository extends JpaRepository<CombinedOrder, Long> {

	List<CombinedOrder> findAllById(Long id);
}
