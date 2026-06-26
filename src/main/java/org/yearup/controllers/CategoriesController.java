package org.yearup.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.service.CategoryService;
import org.yearup.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin

public class CategoriesController
{
    private CategoryService categoryService;
    private ProductService productService;


    // create an Autowired constructor to inject the categoryService and productService
    @Autowired
    public CategoriesController(CategoryService categoryService, ProductService productService)
    {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    // [ COMPLETE ] add the appropriate annotation for a get action
    @GetMapping("")
    @PreAuthorize("permitAll()")
    public List<Category> getAll()
    {
        // [ COMPLETE ] find and return all categories
        // [ IN PROGRESS ] business logic for 'getAllCategories'
        return categoryService.getAllCategories();
    }

    // [ COMPLETE ] add the appropriate annotation for a get action
    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id)
    {
        // [ COMPLETE ] get the category by id
        // [ IN PROGRESS ] business logic for 'getById'
        Category category = categoryService.getById(id);

        if (category == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return category;
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // [ COMPLETE ] get a list of product by categoryId
        return productService.listByCategoryId(categoryId);
    }

    // [ COMPLETE ] add annotation to call this method for a POST action
    @PostMapping()
    // [ COMPLETE ] add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category)
    {
        // [ COMPLETE ] insert the category and return it with status 201 Created
        Category saved = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // [ COMPLETE ] add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PutMapping("{id}")
    // [ COMPLETE ] add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // [ COMPLETE ] update the category by id and return the updated category (200 OK)
        if (categoryService.getById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return categoryService.update(id, category);
    }


    // [ COMPLETE ] add annotation to call this method for a DELETE action - the url path must include the categoryId
    @DeleteMapping("{id}")
    // [ COMPLETE ] add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id)
    {
        // [ COMPLETE ] delete the category by id and return status 204 No Content
        if (categoryService.getById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
