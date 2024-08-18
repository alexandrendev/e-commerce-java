package com.alexandre.eCommerce.services.order;

import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.order.Order;
import com.alexandre.eCommerce.Domain.order.OrderItem;
import com.alexandre.eCommerce.Domain.order.OrderStatus;
import com.alexandre.eCommerce.Domain.order.PaymentMethod;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import com.alexandre.eCommerce.exceptions.inventory.OutOfStockException;
import com.alexandre.eCommerce.repositories.order.OrderItemRepository;
import com.alexandre.eCommerce.repositories.order.OrderRepository;
import com.alexandre.eCommerce.services.cart.CartProductService;
import com.alexandre.eCommerce.services.cart.CartService;
import com.alexandre.eCommerce.services.inventory.InventoryService;
import com.alexandre.eCommerce.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    UserService userService;
    CartService cartService;
    CartProductService cartProductService;
    InventoryService inventoryService;

    OrderRepository repository;
    OrderItemRepository orderItemRepository;


    @Transactional
    //STATUS, TOTALAMOUNT, PAYMENTMETHOD, USER, --ADDRESS--
    public Order createOrder(Long userId, UserAddress address, PaymentMethod method) {

        User user = userService.findUserById(userId);
        if(user == null) return null;

        Order order = Order.builder().
                user(user).
                status(OrderStatus.PENDING_PAYMENT).
                address(address).
                paymentMethod(method).
                build();

        Cart cart = cartService.findCartByUserId(user.getId());
        List<Product> products = cartService.getProducts(cart.getId());

        Map<Long, Long> ocurrences = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getId,
                        Collectors.counting()
                ));

        for (Map.Entry<Long, Long> value : ocurrences.entrySet()) {
            if (!inventoryService.checkInventory(value.getKey(), value.getValue())){
                throw new OutOfStockException("Out of stock");
            }
        }

        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        Order persistedOrder = repository.save(order);

        if (persistedOrder.getOrderItems() == null) {
            persistedOrder.setOrderItems(new ArrayList<>());
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (Product product : products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);


        for (OrderItem orderItem : persistedOrder.getOrderItems()){
            inventoryService.decrementInventory(orderItem.getProduct().getId(), 1);
        }

        cartProductService.cleanCart(cart.getId());


        return order;
//        // set user -> set address -> setPrice - > setStatus
    }

    @Autowired
    public OrderService(UserService userService, CartService cartService, OrderRepository repository,
                        CartProductService cartProductService,
                        OrderItemRepository orderItemRepository,
                        InventoryService inventoryService) {
        this.userService = userService;
        this.cartService = cartService;
        this.repository = repository;
        this.cartProductService = cartProductService;
        this.orderItemRepository = orderItemRepository;
        this.inventoryService = inventoryService;
    }
}
