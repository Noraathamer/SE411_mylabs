package psu.se411.lab13.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psu.se411.lab13.model.Product;
import psu.se411.lab13.model.ProductRepository;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {
	
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
	    this.productRepository = productRepository;
	    
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Product>> findAll() {
			   return ResponseEntity.ok(productRepository.findAll());
		}

}
