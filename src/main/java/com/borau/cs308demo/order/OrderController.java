package com.borau.cs308demo.order;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RequestMapping("/api")
@Service
public class OrderController {

    private final OrderService orderService;







/*
    @GetMapping("/orders")
    public List<Order> fetchAllOrdersByUserId(HttpServletRequest request) {
        return this.customerService.fetchAllOrdersByUserId(request);
    }


    @PostMapping("/order/create")
    public OrderResponse createOrder(HttpServletRequest request) {
        return this.customerService.createOrder(request);
    }
*/


}
