package com.casestudy.OrdersService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.OrdersService.entity.Cart;
import com.casestudy.OrdersService.exception.ItemNotAvailableException;
import com.casestudy.OrdersService.repo.InventoryServiceClient;
import com.casestudy.OrdersService.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired(required=false)
	private CartService cartService;


   
    
  
    @PostMapping("/addItem")
    public ResponseEntity<String> addItemToCart(@RequestBody Cart cartItem) throws ItemNotAvailableException {
        // Add the item to the cart
        cartService.addItemToCart(cartItem);

        return ResponseEntity.ok("Item added to the cart successfully!");
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long itemId) {
        // Remove the item from the cart
        cartService.removeItemFromCart(itemId);

        return ResponseEntity.ok("Item removed from the cart successfully!");
    }

 
    @DeleteMapping("/removeItemsByBatchId/{batchId}")
    public void removeItemsByBatchId(@PathVariable String batchId) {
        // Remove the items from the cart based on the batch ID
        cartService.removeItemsByBatchId(batchId);

    }

    
}
