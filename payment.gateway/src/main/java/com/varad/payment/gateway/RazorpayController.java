package com.varad.payment.gateway;
import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.varad.payment.gateway.entity.OrderRequest;
import com.varad.payment.gateway.entity.OrderResponse;
import com.varad.payment.gateway.proxy.InventoryServiceClient;
import com.varad.payment.gateway.proxy.PgService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pg")
@CrossOrigin(origins = "http://localhost:4200")
public class RazorpayController {

	@Autowired(required = false)
    private InventoryServiceClient inventoryServiceClient;
    
//
//	  @Autowired
//	  public RazorpayController(InventoryServiceClient inventoryServiceClient) {
//	    this.inventoryServiceClient = inventoryServiceClient;
//	  }

	
	private RazorpayClient client;
	private static final String SECRET_ID1 = "rzp_test_mMYCMsqoLV36CI";
	private static final String SECRET_KEY1 = "9Psarzgl5UvRK5IgcfWGpu5T";
	private static final String SECRET_ID2 = "rzp_test_mMYCMsqoLV36CI";
	private static final String SECRET_KEY2 = "9Psarzgl5UvRK5IgcfWGpu5T";
	
	
	

	@PostMapping(path = "/createOrder")
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse response = new OrderResponse();
		try {

			if (orderRequest.getAmount() > 1000) {
				client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
			} else {
				client = new RazorpayClient(SECRET_ID2, SECRET_KEY2);
			}

			Order order = createRazorPayOrder(orderRequest.getAmount());
			System.out.println("---------------------------");
			String orderId = (String) order.get("id");
			System.out.println("Order ID: " + orderId);
			System.out.println("---------------------------");
			System.out.println("PAYMENT PROPERTY"+orderRequest.getPaymentStatus());
			response.setRazorpayOrderId(orderId);
			response.setApplicationFee("" + orderRequest.getAmount());
			if (orderRequest.getAmount() > 1000) {
				response.setSecretKey(SECRET_KEY1);
				response.setSecretId(SECRET_ID1);
				response.setPgName("razor1");
			} else {
				response.setSecretKey(SECRET_KEY2);
				response.setSecretId(SECRET_ID2);
				response.setPgName("razor2");
			}

			return response;
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;

	}
	
	
	 @PostMapping("/status")
	  public void receivePropertyFromFrontend(@RequestBody OrderResponse yourDTO) {
	    // Access the property from the DTO and process it as needed
	    System.out.println("Received property: " + yourDTO.getPaymentStatus());
	  }
	 
	 @DeleteMapping("/deleteDrug/{batchId}")
	 @Transactional
	  public void deleteDrugFromInventory(@PathVariable String batchId) {
	    // Call the Feign client method to delete the drug from the inventory microservice
		System.out.println("FINALLY SUCCESSFULL");
		inventoryServiceClient.deleteAvailableStockByBatchId(batchId);
	  }

	private Order createRazorPayOrder(int i) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", 100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.orders.create(options);
	}
}