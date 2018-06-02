package com.example.Herschel.controller;

import com.example.Herschel.exception.ResourceNotFoundException;
import com.example.Herschel.model.Product;
import com.example.Herschel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product)
    {
        return productRepository.save(product);
    }

    @GetMapping("/findProductById/{id}")
    public Product findProductById(@PathVariable(value = "id") Long id)
    {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @GetMapping("/findByType/{type}")
    public List<Product> getSpecificProductType(@PathVariable(value = "type") String type)
    {
        return productRepository.findAllByProductType(type);
    }

    @GetMapping("/checkAvailability/{id}")
    public boolean checkProduct(@PathVariable(value = "id") Long productId, @RequestBody int requiredNumber)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        if(product.getAvailable() >= requiredNumber)
            return true;
        return false;
    }

    @GetMapping("/buyProduct/{id}")
    public boolean buyProduct(@PathVariable(value = "id") Long productId, @RequestBody int requiredNumber)
    {
        if(!checkProduct(productId, requiredNumber))
            return false;
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        int availNumber = product.getAvailable();
        availNumber -= requiredNumber;
        product.setAvailable(availNumber);
        productRepository.save(product);
        return true;
    }
}
