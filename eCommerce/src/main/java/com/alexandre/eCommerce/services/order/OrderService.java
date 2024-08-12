package com.alexandre.eCommerce.services.order;

import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.order.Order;
import com.alexandre.eCommerce.Domain.order.OrderStatus;
import com.alexandre.eCommerce.Domain.order.PaymentMethod;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import com.alexandre.eCommerce.repositories.order.OrderRepository;
import com.alexandre.eCommerce.services.cart.CartProductService;
import com.alexandre.eCommerce.services.cart.CartService;
import com.alexandre.eCommerce.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    UserService userService;
    CartService cartService;
    CartProductService cartProductService;

    OrderRepository repository;


    //STATUS, TOTALAMOUNT, PAYMENTMETHOD, USER, --ADDRESS--
    public Order createOrder(Long userId, UserAddress address, PaymentMethod method) {
        Order order = new Order();
        User user = userService.findUserById(userId);
        if(user == null) return null;
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        order.setAddress(address);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<Product> products = cartService.getProducts(cart.getId());

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(Product product: products){
            totalAmount = totalAmount.add(product.getPrice());
        }
        order.setTotalAmount(totalAmount);
        Order persistedOrder = repository.save(order);
        if(persistedOrder != null){
            cartProductService.cleanCart(cart.getId());
        }

        return order;
//        // set user -> set address -> setPrice - > setStatus
    }

    @Autowired
    public OrderService(UserService userService, CartService cartService, OrderRepository repository, CartProductService cartProductService) {
        this.userService = userService;
        this.cartService = cartService;
        this.repository = repository;
        this.cartProductService = cartProductService;
    }
}
