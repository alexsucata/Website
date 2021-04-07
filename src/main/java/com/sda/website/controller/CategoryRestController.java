package com.sda.website.controller;

import com.sda.website.entity.CategoryEntity;
import com.sda.website.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryEntity getCategoryById(@PathVariable Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @PostMapping("/categories/add")
    public ResponseEntity<String> addNewCategory(@Valid @RequestBody CategoryEntity categoryEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(element -> element.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(412).body(errors);
        } else {
            categoryRepository.save(categoryEntity);
            return ResponseEntity.ok().body("success");
        }
    }

    @PutMapping("/categories/update/optional/{categoryId}")
    public String updateCategoryWithOptional(@PathVariable Integer categoryId,@Valid @RequestBody CategoryEntity categoryEntity) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);

        if(!category.isPresent()) {
            return "category: " + categoryEntity.getName() + " does not exist in database";
        } else {
            category.get().setName(categoryEntity.getName());
            categoryRepository.save(category.get());
            return "category: " + category.get().getName() + " was updated";
        }
    }
    //varianta de update
    @PutMapping("/categories/update/{categoryId}")
    public CategoryEntity updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryEntity categoryEntity) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return categoryEntity;
        } else {
            category.setName(categoryEntity.getName());
            return categoryRepository.save(category);
        }
    }

    @DeleteMapping("/categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return "Category: " + category.get().getName() + " was deleted";
        } else {
            return "Record not found";
        }
    }
}
