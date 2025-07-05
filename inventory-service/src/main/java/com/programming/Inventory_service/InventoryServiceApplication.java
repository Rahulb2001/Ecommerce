package com.programming.Inventory_service;

import com.programming.Inventory_service.Repository.InventoryRepository;
import com.programming.Inventory_service.model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {

		SpringApplication.run(InventoryServiceApplication.class, args);
    }

	@Bean
	public CommandLineRunner loadData(InventoryRepository inverntory){
		return (args) -> {

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13");
			inventory1.setQuantity(100);
			inverntory.save(inventory1);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_14");
			inventory2.setQuantity(0);
			inverntory.save(inventory2);


			System.out.println("Inventory service is running and ready to accept requests.");
		};




	}

}
