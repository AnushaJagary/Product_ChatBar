
package com.ojas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojas.entity.ProductsEntity;

@Component
public class ProductJsonReader {

	public List<ProductsEntity> readProductFromJson(){
		ClassPathResource resource = new ClassPathResource("products1.json");
		
			InputStream inputStream;
			try {
				inputStream = resource.getInputStream();
				byte[] bytes = inputStream.readAllBytes();
				String responseData = new String(bytes, StandardCharsets.UTF_8);
				// Parse JSON string into a list of Product objects
				ObjectMapper objectMapper = new ObjectMapper();
				List<ProductsEntity> products = objectMapper.readValue(responseData,
						new TypeReference<List<ProductsEntity>>() {
						});
				
				return products;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
}

	
	public void setPathForProducts(List<ProductsEntity> products) {
		   for(ProductsEntity product:products) {
			   product.setPath("");
		   }
	}
	
	
}
