package com.alexandre.eCommerce.Controller.order;

import com.alexandre.eCommerce.Domain.order.Order;
import com.alexandre.eCommerce.Domain.order.dtos.CreateOrderRequest;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import com.alexandre.eCommerce.services.TokenUtil;
import com.alexandre.eCommerce.services.order.OrderService;
import com.alexandre.eCommerce.services.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    OrderService service;
    TokenUtil tokenUtil;
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Order> proceedWithTheOrder(@RequestHeader String tokenHeader, @RequestBody CreateOrderRequest data){
        var userId = tokenUtil.extractIdFromToken(tokenHeader);
        UserAddress address = addressService.getAddressById(data.addressId());
        if(address == null) ResponseEntity.notFound().build();

        Order newOrder = service.createOrder(userId, address, data.method());

        return ResponseEntity.ok(newOrder);
    }

    @Autowired
    public OrderController(OrderService service, TokenUtil tokenUtil, AddressService addressService) {
        this.service = service;
        this.tokenUtil = tokenUtil;
        this.addressService = addressService;
    }
}
