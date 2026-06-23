package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
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
        // act
        // assert
    }



}