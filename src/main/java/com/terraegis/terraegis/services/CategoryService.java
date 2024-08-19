package com.terraegis.terraegis.services;

import  com.terraegis.terraegis.models.Category;
import  com.terraegis.terraegis.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Optional<List<Category>> getCategoriesByProjectId(Long projectId) {
        return categoryRepository.findCategoriesByProjectId(projectId);
    }

    public Optional<List<Category>> getAllCategories() {
        return Optional.of(categoryRepository.findAll());
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category updatedCategory = category.get();
            updatedCategory.setName(categoryDetails.getName());
            updatedCategory.setDescription(categoryDetails.getDescription());
            updatedCategory.setProject(categoryDetails.getProject());
            return categoryRepository.save(updatedCategory);
        } else {
            return null;
        }
    }

    public boolean deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<List<Category>> getCategoriesByCreatorId(Long creatorId) {
        return categoryRepository.findCategoriesByCreatorId(creatorId);
    }

    public Optional<List<Category>> getCategoriesByProjectId(Long projectId) {
        return categoryRepository.findCategoriesByProjectId(projectId);
    }
}
