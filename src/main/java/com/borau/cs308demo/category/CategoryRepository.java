package com.borau.cs308demo.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {


    List<Category> findByIsActiveTrue();

    Boolean existsByName(String name);

    Optional<Category> findByNameAndIsActiveTrue(String name);
}
