package com.borau.cs308demo.category;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
//@Secured({"ROLE_ADMIN", "ROLE_PRODUCTMANAGER"})
@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public Boolean existCategory(String name) {
        return categoryRepo.existsByName(name);
    }

    public List<Category> getAllActiveCategory() {
        List<Category> categories = categoryRepo.findByIsActiveTrue();
        return categories;
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();

    }

    public Optional<Category> findById(String id) {
        return categoryRepo.findById(id);

    }

    public Category save(Category category) {
        return categoryRepo.save(category);

    }

    public void deleteById(String id) {
        categoryRepo.deleteById(id);

    }

    //Productmanager controllerde çağrılır.
    public Category update(String id, Category categoryDetails) {
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    return categoryRepo.save(category);
                })
                .orElseGet(() -> {
                    categoryDetails.setId(id);
                    return categoryRepo.save(categoryDetails);
                });
    }
}












