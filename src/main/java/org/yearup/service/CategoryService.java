package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        // [ COMPLETE ] get all categories
        return categoryRepository.findAll();
    }

    public Category getById(int categoryId)
    {
        // [ COMPLETE ] get category by id
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category create(Category category)
    {
        // [ COMPLETE ] create a new category
        category.setCategoryId(0); // review later
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category)
    {   // we don't need to update Category_Id (primary key)
        // [ COMPLETE ] update category and return the updated category
        Category existing = categoryRepository.findById(categoryId).orElseThrow();
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return categoryRepository.save(existing);
    }

    public void delete(int categoryId)
    {
        // [ COMPLETE ] delete category
        categoryRepository.deleteById(categoryId);
    }
}
