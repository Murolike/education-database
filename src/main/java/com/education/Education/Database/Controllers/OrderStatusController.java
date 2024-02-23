package com.education.Education.Database.Controllers;

import com.education.Education.Database.Responses.OrderStatusResponse;
import com.education.Education.Database.Services.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-status")
@Tag(name = "OrderStatus", description = "Управление статусам")
public class OrderStatusController {
    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    @Operation(summary = "Получить список", tags = {"OrderStatus"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = OrderStatusResponse.class)),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    public Iterable<OrderStatusResponse> index() {
        return this.orderStatusService.findAll();
    }
}
