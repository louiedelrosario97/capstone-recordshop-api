package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        // Instructions: load the user's cart rows, look up each product, and build the ShoppingCart

        // we want data from our DB, so we create a list that calls the repository. (repo connects to DB)
        List<CartItem> cartById = shoppingCartRepository.findByUserId(userId);

        // CartItem's variables are just IDs, so we need to use the 'productId' to fetch the full data
        // we'll use ProductService's getById() and pass in CartItem's getProductId()

        for (CartItem cartItem : cartById)
        {
            Product product = productService.getById(cartItem.getProductId());
        }

        return
    }

    // add additional methods here
}
