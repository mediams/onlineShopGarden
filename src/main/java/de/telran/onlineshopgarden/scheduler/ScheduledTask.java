package de.telran.onlineshopgarden.scheduler;

import de.telran.onlineshopgarden.entity.Order;
import de.telran.onlineshopgarden.entity.enums.OrderStatus;
import de.telran.onlineshopgarden.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    private final OrderRepository orderRepository;

    @Autowired
    public ScheduledTask(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void taskChangeOrderStatusForDelivery() {
        List<Order> orders = orderRepository.findByStatusNotIn(List.of(OrderStatus.CANCELED, OrderStatus.DELIVERED));
        for (Order order : orders) {
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            orderRepository.save(order);
        }
    }
}
