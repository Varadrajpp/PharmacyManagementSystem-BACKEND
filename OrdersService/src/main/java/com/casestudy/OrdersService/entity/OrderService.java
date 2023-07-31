package com.casestudy.OrdersService.entity;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    public List<CombinedOrder> getAllOrders();

    public Optional<CombinedOrder> findOrderById(int id);

    public CombinedOrder saveOrder(CombinedOrder order);

    public void deleteOrderById(int id);

    //public String placeOrder(Order order);
}
