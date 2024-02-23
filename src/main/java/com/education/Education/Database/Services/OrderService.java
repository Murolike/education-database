package com.education.Education.Database.Services;

import com.education.Education.Database.DTO.OrderDTO;
import com.education.Education.Database.Exceptions.NotFoundException;
import com.education.Education.Database.Models.Order;
import com.education.Education.Database.Models.OrderStatus;
import com.education.Education.Database.Repositories.OrderRepository;
import com.education.Education.Database.Responses.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderStatusService orderStatusService;

    public Order create(OrderDTO orderDTO) {
        Order model = new Order();
        model.setUser(orderDTO.getUser());
        model.setProduct(orderDTO.getProduct());
        model.setOrderStatus(orderDTO.getStatus());
        model.setCreatedAt(LocalDateTime.now());

        return this.repository.save(model);
    }

    public Order update(Order order, OrderDTO orderDTO) {
        order.setUser(orderDTO.getUser());
        order.setProduct(orderDTO.getProduct());
        order.setOrderStatus(orderDTO.getStatus());
        order.setUpdatedAt(LocalDateTime.now());

        return this.repository.save(order);
    }

    public OrderResponse getSingleResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .user(this.userService.getSingleResponse(order.getUser()))
                .product(this.productService.getSingleResponse(order.getProduct()))
                .orderStatus(this.orderStatusService.getSingleResponse(order.getOrderStatus()))
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public void delete(Order order) {
        this.repository.delete(order);
    }

    public Order findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Iterable<OrderResponse> findAll() {
        Iterable<Order> cursor = this.repository.findAll();
        ArrayList<OrderResponse> models = new ArrayList<>();

        for (Order order : cursor) {
            OrderResponse model = this.getSingleResponse(order);

            models.add(model);
        }

        return models;
    }

    public Order changeStatus(Order order, OrderStatus orderStatus) {
        order.setOrderStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());

        this.repository.save(order);

        return order;
    }
}
