package com.bew.SpringProj.servie;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bew.SpringProj.model.Product;
import com.bew.SpringProj.repository.ProductRepo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(int id) {
		return productRepo.findById(id).orElse(new Product(-1));
	}

	public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException {

		Product existingProduct = null;
		if (product.getId() != 0) {
			existingProduct = productRepo.findById(product.getId()).orElse(null);
		}
		if (imageFile != null && !imageFile.isEmpty()) {
			product.setImageName(imageFile.getOriginalFilename());
			product.setImageType(imageFile.getContentType());
			product.setImageData(imageFile.getBytes());
		}
		else if (existingProduct != null) {
			product.setImageName(existingProduct.getImageName());
			product.setImageType(existingProduct.getImageType());
			product.setImageData(existingProduct.getImageData());
		}
		return productRepo.save(product);
	}

	public void deleteProduct(int id) { productRepo.deleteById(id);
	}

	public List<Product> searchProduct(String keyword) { return productRepo.searchProduct(keyword);
	}
}
