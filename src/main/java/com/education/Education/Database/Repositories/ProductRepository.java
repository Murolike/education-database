package com.education.Education.Database.Repositories;

import com.education.Education.Database.Models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
