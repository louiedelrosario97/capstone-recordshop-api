package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// workbook 8a pg. 89 - how to write tests for Service Layer
@ExtendWith(MockitoExtension.class)
class ProductServiceTest
{
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void searchNoFiltersApplied_shouldReturnBoth_featuredAndNonFeaturedProducts()
    {
        // arrange
        Product featured = new Product();
        featured.setFeatured(true);
        Product notFeatured = new Product();
        notFeatured.setFeatured(false);

        List<Product> mockList = new ArrayList<>();
        mockList.add(featured);
        mockList.add(notFeatured);

        when(productRepository.findAll()).thenReturn(mockList);

        // act
        List<Product> results = productService.search(null, null, null, null);

        // assert
        assertEquals(2, results.size());
    }

    @Test
    public void update_shouldCallSetStock_andUpdateToSetValue() // IC!
    {
        // arrange
        Product product = new Product();
        product.setStock(50);

        Product productStockUpdated = new Product();
        product.setStock(100);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        // act
        Product result = productService.update(1, productStockUpdated);

        // assert
        assertEquals(100, result.getStock());
    }
}