package com.sda.website.controller;


import com.sda.website.entity.OrderEntity;
import com.sda.website.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{orderId}")
    public OrderEntity getOrderById(@PathVariable Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @PostMapping("/orders/add")
    public OrderEntity addNewOrder(@RequestBody OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @PutMapping("/orders/update/{orderId}")
    public OrderEntity updateOrder(@PathVariable Integer orderId,@RequestBody OrderEntity orderEntity) {
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            return orderEntity;
        }
        else {
            order.setOrderDate(orderEntity.getOrderDate());
            order.setClient(orderEntity.getClient());
            order.setProducts(orderEntity.getProducts());
            return orderRepository.save(order);
        }
    }

    @DeleteMapping("/orders/delete/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            orderRepository.delete(order.get());
            return "Order no: " +order.get().getOrderId() + " from date: "+ order.get().getOrderDate()+ " was deleted";
        }
        else {
            return  "Record not found";
        }

    }

}
