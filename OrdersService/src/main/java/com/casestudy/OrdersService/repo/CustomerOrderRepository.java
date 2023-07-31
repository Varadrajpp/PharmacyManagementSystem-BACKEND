package com.casestudy.OrdersService.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.OrdersService.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
