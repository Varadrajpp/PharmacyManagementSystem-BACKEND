package com.casestudy.OrdersService.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.OrdersService.entity.DoctorOrder;

public interface DoctorOrderRepository extends JpaRepository<DoctorOrder, Long> {

	



	boolean existsByDoctorName(String doctorName);
}
