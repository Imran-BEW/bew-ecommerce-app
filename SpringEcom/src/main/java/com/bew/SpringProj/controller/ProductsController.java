package com.bew.SpringProj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bew.SpringProj.model.Product;
import com.bew.SpringProj.servie.ProductService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product>getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product.getId() > 0) return new ResponseEntity<>(product,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/product/{id}/image")
	public ResponseEntity<byte[]>getImageByProductId(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product.getId() > 0) return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product savedProduct = productService.addOrUpdateProduct(product, imageFile);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(
			@PathVariable int id,
			@RequestPart("product") Product product,
			@RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

		try {
			product.setId(id);
			productService.addOrUpdateProduct(product, imageFile);
			return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);

		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product != null){
			productService.deleteProduct(id);
			return new ResponseEntity<>("Delete",HttpStatus.OK);
		}else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products = productService.searchProduct(keyword);
		System.out.println("searching with"+ keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
