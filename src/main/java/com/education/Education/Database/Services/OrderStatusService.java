package com.education.Education.Database.Services;

import com.education.Education.Database.Constants.OrderStatusConstants;
import com.education.Education.Database.Exceptions.NotFoundException;
import com.education.Education.Database.Models.OrderStatus;
import com.education.Education.Database.Repositories.OrderStatusRepository;
import com.education.Education.Database.Responses.OrderStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository repository;

    public OrderStatus findCreatedStatus() {
        return this.findById(OrderStatusConstants.CREATED);
    }

    public OrderStatus findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Iterable<OrderStatusResponse> findAll() {
        Iterable<OrderStatus> cursor = this.repository.findAll();
        ArrayList<OrderStatusResponse> models = new ArrayList<>();

        for (OrderStatus orderStatus : cursor) {
            OrderStatusResponse model = this.getSingleResponse(orderStatus);

            models.add(model);
        }

        return models;
    }

    public OrderStatusResponse getSingleResponse(OrderStatus orderStatus) {
        return OrderStatusResponse.builder()
                .id(orderStatus.getId())
                .name(orderStatus.getName())
                .build();
    }
}
