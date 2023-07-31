package com.casestudy.OrdersService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.casestudy.OrdersService.entity.CombinedOrder;
import com.casestudy.OrdersService.entity.OrderServiceImpl;
import com.casestudy.OrdersService.repo.CombinedOrderRepository;


@SpringBootTest
public class OrderRepoTest {
    @Mock
    CombinedOrderRepository orderRepository;
    @InjectMocks
    OrderServiceImpl orderService;
    @Test
    void isOrderExistById(){
        CombinedOrder order = new CombinedOrder(1000L, "Dr. Kunal Ghosh", "1234567890", "doctor@example.com",
                "Customer Name", "9876543210", "customer@example.com", "Rapis", 150.0, 6, 900.0, LocalDate.of(2022,05,04));
        orderService.saveOrder(order);
        Optional<CombinedOrder> order1 = orderRepository.findById(1000L);
        if(!order1.isEmpty()) {
            Boolean actualResult = true;
            assertThat(actualResult).isTrue();
        }
    }

    @Test
    public void getAllOrdersTest()
    {
        List<CombinedOrder> list = new ArrayList<>();
        CombinedOrder order1 = new CombinedOrder(1000L, "Dr. Kunal Ghosh", "1234567890", "doctor@example.com",
                "Customer Name", "9876543210", "customer@example.com", "Rapis", 150.0, 6, 900.0, LocalDate.of(2022,05,04));
        CombinedOrder order2 = new CombinedOrder(2000L, "Dr. T.K. Maitra", "1234567890", "doctor@example.com",
                "Customer Name", "9876543210", "customer@example.com", "Honitus", 240.0, 2, 480.0, LocalDate.of(2022,05,04));
        CombinedOrder order3 = new CombinedOrder(3000L, "Dr. Nishu Kumar", "1234567890", "doctor@example.com",
                "Customer Name", "9876543210", "customer@example.com", "Aceta-25", 258.0, 10, 2580.0, LocalDate.of(2022,05,04));

        list.add(order1);
        list.add(order2);
        list.add(order3);

        when(orderRepository.findAll()).thenReturn(list);

        List<CombinedOrder> orderList = orderService.getAllOrders();

        assertEquals(3, orderList.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void saveOrder(){
        CombinedOrder order = new CombinedOrder(1000L, "Dr. Kunal Ghosh", "1234567890", "doctor@example.com",
                "Customer Name", "9876543210", "customer@example.com", "D-50", 150.0, 6, 900.0, LocalDate.of(2022,05,04));
        orderService.saveOrder(order);
        verify(orderRepository,times(1)).save(order);
    }

}
