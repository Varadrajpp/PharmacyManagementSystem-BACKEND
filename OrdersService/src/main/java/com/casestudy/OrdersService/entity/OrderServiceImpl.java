package com.casestudy.OrdersService.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.OrdersService.repo.CombinedOrderRepository;



@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    CombinedOrderRepository orderRepository;



    @Override
    public List<CombinedOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<CombinedOrder> findOrderById(int id) {
        return orderRepository.findById((long) id);
    }

    @Override
    public CombinedOrder saveOrder(CombinedOrder order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById((long) id);
    }

//    @Override
//    public String placeOrder(Order order) {
//
//        orderRepository.placeOrder(order);
//        return "";
//    }
}
