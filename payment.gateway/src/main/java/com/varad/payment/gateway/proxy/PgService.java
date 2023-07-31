package com.varad.payment.gateway.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PgService {

   


	@Autowired(required = false)
    private InventoryServiceClient inventoryServiceClient;
    

    public void removefrominventoryFromCart(String batchId) {
      
    	inventoryServiceClient.deleteAvailableStockByBatchId(batchId);
    }


   
}

