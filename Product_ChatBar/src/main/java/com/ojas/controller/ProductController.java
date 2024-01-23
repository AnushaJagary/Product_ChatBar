package com.ojas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojas.entity.ProductsEntity;
import com.ojas.service.ProductService;

@Controller
//@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final ProductJsonReader productJsonReader;

	@Autowired
	public ProductController(ProductService productService, ProductJsonReader productJsonReader) {
		this.productService = productService;
		this.productJsonReader = productJsonReader;
	}

	// @GetMapping(value = "/api/data")
	@GetMapping("/")
	public String getJSONData(Model model) {
		List<ProductsEntity> products = productJsonReader.readProductFromJson();
		model.addAttribute("products", products);
		List<String> productNames = products.stream().map(ProductsEntity::getName).collect(Collectors.toList());
		List<Double> productPrice = products.stream().map(ProductsEntity::getPrice).collect(Collectors.toList());
		model.addAttribute("productLabels", productNames);
		model.addAttribute("productData", productPrice);
		long cheapCount = products.stream().filter(p -> p.getPrice() < 10000).count();
		long mediumCount = products.stream().filter(p -> p.getPrice() >= 10000 && p.getPrice() <= 50000).count();
		long inExpensive = products.stream().filter(p -> p.getPrice() >= 50000 && p.getPrice() <= 80000).count();
		long expensive = products.stream().filter(p -> p.getPrice() >= 100000).count();
		// long expensive = products.size() - cheapCount - mediumCount - inExpensive;
		model.addAttribute("cheapCount", cheapCount);
		model.addAttribute("mediumCount", mediumCount);
		model.addAttribute("expensiveCount", expensive);
		model.addAttribute("inexpensiveCount", inExpensive);
		return "productList.html";

	}

	@GetMapping("/datalist")
//	@ResponseBody
	public String productList(Model model) {
		List<ProductsEntity> products = productService.getAllProducts();
		model.addAttribute("products", products);
		List<String> productNames = products.stream().map(ProductsEntity::getName).collect(Collectors.toList());
		List<Double> productPrices = products.stream().map(ProductsEntity::getPrice).collect(Collectors.toList());
		model.addAttribute("productLabels", productNames);
		model.addAttribute("productData", productPrices);
		long expensiveCount = products.stream().filter(p -> p.getPrice() >= 50000).count();
		long inexpensiveCount = products.stream().filter(p -> p.getPrice() < 50000).count();
		model.addAttribute("expensiveCount", expensiveCount);
		model.addAttribute("inexpensiveCount", inexpensiveCount);
		return "productList.html";
	}

	@GetMapping("/show/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		Optional<ProductsEntity> product = productService.getProductById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		}
		return "showProduct.html";
	}

	@GetMapping("/create")
	public String createProduct(Model model) {
		// ProductsEntity productsEntity =
		model.addAttribute("product", new ProductsEntity());
		return "createProduct.html";
	}

	@PostMapping("/save")
	public String saveOrUpdateProduct(@ModelAttribute ProductsEntity productsEntity, Model model) {
		productService.createOrUpdateProduct(productsEntity);
		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Optional<ProductsEntity> product = productService.getProductById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		}
		return "editProduct.html";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, @ModelAttribute ProductsEntity productsEntity) {
		Optional<ProductsEntity> product = productService.getProductById(id);
		if (product.isPresent()) {
			productsEntity.setId(id);
			productService.createOrUpdateProduct(productsEntity);
		}
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/";

	}

	
}
