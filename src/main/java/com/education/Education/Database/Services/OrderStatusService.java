package com.education.Education.Database.Services;

import com.education.Education.Database.Constants.OrderStatusConstants;
import com.education.Education.Database.Exceptions.NotFoundException;
import com.education.Education.Database.Models.OrderStatus;
import com.education.Education.Database.Repositories.OrderStatusRepository;
import com.education.Education.Database.Responses.OrderStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OrderStatusResponse getSingleResponse(OrderStatus orderStatus) {
        return OrderStatusResponse.builder()
                .id(orderStatus.getId())
                .name(orderStatus.getName())
                .build();
    }
}
