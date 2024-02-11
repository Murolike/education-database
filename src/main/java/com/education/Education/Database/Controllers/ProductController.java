package com.education.Education.Database.Controllers;

import com.education.Education.Database.Forms.ProductForm;
import com.education.Education.Database.Models.Product;
import com.education.Education.Database.Responses.ProductResponse;
import com.education.Education.Database.Services.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product", description = "Управление продуктами")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Получить список", tags = {"Product"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class)),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    public Iterable<ProductResponse> index() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить по идентификатору", tags = {"Product"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json")})
    })
    public ProductResponse get(@PathVariable("id") Long id) {
        Product product = this.productService.findById(id);

        return this.productService.getSingleResponse(product);
    }

    @PostMapping
    @Operation(summary = "Создание", tags = {"Product"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json")})
    })
    public ProductResponse create(@Valid @RequestBody ProductForm productForm) {
        Product product = this.productService.create(productForm);

        return this.productService.getSingleResponse(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление", tags = {"Product"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json")})
    })
    public ProductResponse update(@PathVariable("id") Long id, @Valid @RequestBody ProductForm productForm) {
        Product product = this.productService.findById(id);
        product = this.productService.update(product, productForm);
        return this.productService.getSingleResponse(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", tags = {"Product"})
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public void delete(@PathVariable("id") Long id) {
        Product product = this.productService.findById(id);
        this.productService.delete(product);
    }
}
