package com.casestudy.OrdersService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.OrdersService.entity.AvailableStock;
import com.casestudy.OrdersService.entity.Cart;
import com.casestudy.OrdersService.exception.ItemNotAvailableException;
import com.casestudy.OrdersService.repo.Cartrepository;
import com.casestudy.OrdersService.repo.InventoryServiceClient;

@Service
public class CartService {

   
    
	@Autowired(required = false)
    private Cartrepository cartRepository;

	@Autowired(required = false)
    private InventoryServiceClient inventoryServiceClient;
    


    public void addItemToCart(Cart cart) throws ItemNotAvailableException {
        // Check if the item is available in the inventory
      
        AvailableStock item=inventoryServiceClient.getAvailableStockByBatchId(cart.getBatchId());
        if (item != null && item.getQuantity() >= cart.getQuantity()) {
            
            Cart cartItem = new Cart();
     
            cartRepository.save(cart);
        } else {
            throw new ItemNotAvailableException("Item is not available in the inventory.");
        }
    }

    public void removeItemFromCart(Long itemId) {
      
        cartRepository.deleteById(itemId);
    }

    public void removeItemsByBatchId(String batchId) {
       // List<Cart> cartItems = cartRepository.findByBatchId(batchId);
        cartRepository.deleteByBatchId(batchId);
        // Optionally, you can perform additional logic such as logging, updating inventory, etc.
    }
   
}

