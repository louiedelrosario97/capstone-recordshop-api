package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;
// business logic that ShoppingCartController plugs into
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
//---------------------------------------- [ GET getByUserId() ] --------------------------------------------------

    public ShoppingCart getByUserId(int userId)
    {
        // instructions: load the user's cart rows, look up each product, and build the ShoppingCart

        // notes:
        // we want data from our DB, so we create a list that calls the repository. (repo connects to DB)
        // CartItem's variables are just IDs, so we need to use the 'productId' to fetch the full data
        // we'll use ProductService's getById() and pass in CartItem's getProductId()
        // ShoppingCartItem combines the Product data from ProductService with the quantity from the CartItem row.

        List<CartItem> usersCart = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        for (CartItem cartItem : usersCart)
        {
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setProduct(product);

            shoppingCartItem.setQuantity(cartItem.getQuantity());

            cart.add(shoppingCartItem);
        }
        return cart;
    }

// ----------------------------------- [ POST - updateCart() ] --------------------------------------------------------

    public ShoppingCart updateProduct(int userId, int productId, int quantity)
    {

    }


// ----------------------------------- [ DELETE - clearCart() ] -------------------------------------------------------

    public void clearCart(int userId)
    {

    }

// ----------------------------------- [ PUT - addProductToCart() ] ---------------------------------------------------

    public ShoppingCart addProductToCart(int userId, int productId)
    {

    }
}
