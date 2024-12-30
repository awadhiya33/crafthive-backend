package com.example.crafthive.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.crafthive.entity.ImageModel;
import com.example.crafthive.entity.Product;
import com.example.crafthive.service.ProductService;



@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value="/addNewProduct",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addNewProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) {
		try {
			Set<ImageModel> images=uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> ImageModels=new HashSet<>();
		
		for(MultipartFile file:multipartFiles) {
			ImageModel imageModel=new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes());
			ImageModels.add(imageModel);
			
		}
		return ImageModels;
	}
	
	
	@GetMapping({"/getAllProducts"})
	public List<Product> getAllProduct(@RequestParam(defaultValue= "0")int pageNumber,@RequestParam(defaultValue= "")String searchKey){
		List<Product> result = productService.getAllProducts(pageNumber,searchKey);
		System.out.println("Result size is "+ result.size());
	    productService.getAllProducts(pageNumber,searchKey);
	    return result;
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/deleteProductDetails/{productId}")
	public void deleteProductDetails(@PathVariable("productId") int productId) {
		productService.deleteProductDetails(productId);
	}
	
	
	@GetMapping("/getProductDetailsById/{productId}")
	public Product getProductDetailsById(@PathVariable("productId") int productId) {
		return productService.getProductDetailsById(productId);
		
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
	public List<Product> getProductDetails(@PathVariable(name="isSingleProductCheckout") boolean isSingleProductCheckout, @PathVariable(name="productId")int productId) {
		return productService.getProductDetails(isSingleProductCheckout, productId);
		
	}
	
	
}
