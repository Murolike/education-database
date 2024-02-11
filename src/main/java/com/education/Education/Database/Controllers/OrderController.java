package com.education.Education.Database.Controllers;

import com.education.Education.Database.DTO.OrderDTO;
import com.education.Education.Database.Forms.OrderForm;
import com.education.Education.Database.Forms.OrderStatusForm;
import com.education.Education.Database.Models.Order;
import com.education.Education.Database.Models.OrderStatus;
import com.education.Education.Database.Models.Product;
import com.education.Education.Database.Models.User;
import com.education.Education.Database.Responses.OrderResponse;
import com.education.Education.Database.Services.OrderService;
import com.education.Education.Database.Services.OrderStatusService;
import com.education.Education.Database.Services.ProductService;
import com.education.Education.Database.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Tag(name = "Order", description = "Управление заказами")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    @Operation(summary = "Получить список", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class)),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    public Iterable<OrderResponse> index() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить по идентификатору", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json")})
    })
    public OrderResponse get(@PathVariable("id") Long id) {
        Order order = this.orderService.findById(id);

        return this.orderService.getSingleResponse(order);
    }

    @PostMapping
    @Operation(summary = "Создание", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json")})
    })
    public OrderResponse create(@Valid @RequestBody OrderForm orderForm) {
        User user = this.userService.findById(orderForm.getUserId());
        Product product = this.productService.findById(orderForm.getProductId());
        OrderStatus orderStatus = this.orderStatusService.findCreatedStatus();

        OrderDTO orderDTO = OrderDTO.builder()
                .user(user)
                .product(product)
                .status(orderStatus)
                .build();

        Order order = this.orderService.create(orderDTO);

        return this.orderService.getSingleResponse(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json")})
    })
    public OrderResponse update(@PathVariable("id") Long id, @Valid @RequestBody OrderForm orderForm) {
        Order order = this.orderService.findById(id);
        User user = this.userService.findById(orderForm.getUserId());
        Product product = this.productService.findById(orderForm.getProductId());
        OrderStatus orderStatus = this.orderStatusService.findCreatedStatus();

        OrderDTO orderDTO = OrderDTO.builder()
                .user(user)
                .product(product)
                .status(orderStatus)
                .build();
        order = this.orderService.update(order, orderDTO);

        return this.orderService.getSingleResponse(order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public void delete(@PathVariable("id") Long id) {
        Order order = this.orderService.findById(id);
        this.orderService.delete(order);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Смена статуса", tags = {"Order"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json")})
    })
    public OrderResponse status(@PathVariable("id") Long id, @Valid @RequestBody OrderStatusForm orderStatusForm) {
        Order order = this.orderService.findById(id);
        OrderStatus orderStatus = this.orderStatusService.findById(orderStatusForm.getStatusId());

        order = this.orderService.changeStatus(order, orderStatus);

        return this.orderService.getSingleResponse(order);
    }
}
