package com.example.demo.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDetails;


//@SpringBootApplication
@RestController
@RequestMapping("/v2")
public class ProductController {
   public static Map<String, ProductDetails> productRepo = new HashMap<>();
   static {
	   ProductDetails honey = new ProductDetails();
      honey.setId("1");
      honey.setName("Honey");
      productRepo.put(honey.getId(), honey);
      
      ProductDetails almond = new ProductDetails();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
   }

   @RequestMapping(value ="/deleteProduct/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> deleteProduct(@PathVariable String id){
	   System.out.println("INSIDE DELETE PRODUCT");
	   productRepo.remove(id);
	   return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
   }
   
   @RequestMapping(value="/updateProduct/{id}", method= RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody ProductDetails product){
	   System.out.println("INSIDE UPDATE PRODUCT");
	   productRepo.remove(id);
	   productRepo.put(id, product);
	   return new ResponseEntity("Product updated successfully", HttpStatus.OK);
   }

   @RequestMapping(value ="/createProduct", method = {RequestMethod.GET,RequestMethod.POST})
   public ResponseEntity<Object> createProduct(@RequestBody ProductDetails product) {
	   System.out.println("INSIDE CREATE PRODUCT");
	   productRepo.put(product.getId(), product);
	   return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
   }   
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
	   System.out.println("INSIDE GET PRODUCT LIST");
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
	
}