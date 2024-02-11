package com.education.Education.Database.DTO;

import com.education.Education.Database.Models.OrderStatus;
import com.education.Education.Database.Models.Product;
import com.education.Education.Database.Models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDTO {
    private User user;
    private Product product;
    private OrderStatus status;
}
