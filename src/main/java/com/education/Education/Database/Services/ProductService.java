package com.education.Education.Database.Services;

import com.education.Education.Database.Exceptions.NotFoundException;
import com.education.Education.Database.Forms.ProductForm;
import com.education.Education.Database.Models.Product;
import com.education.Education.Database.Repositories.ProductRepository;
import com.education.Education.Database.Responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(ProductForm productForm) {
        Product model = new Product();
        model.setName(productForm.getName());
        model.setPrice(productForm.getPrice());
        model.setCreatedAt(LocalDateTime.now());

        return this.repository.save(model);
    }

    public Product update(Product product, ProductForm productForm) {
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setUpdatedAt(LocalDateTime.now());

        return this.repository.save(product);
    }

    public ProductResponse getSingleResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public void delete(Product product) {
        this.repository.delete(product);
    }

    public Product findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Iterable<ProductResponse> findAll() {
        Iterable<Product> cursor = this.repository.findAll();
        ArrayList<ProductResponse> models = new ArrayList<>();

        for (Product product : cursor) {
            ProductResponse model = this.getSingleResponse(product);

            models.add(model);
        }

        return models;
    }
}
