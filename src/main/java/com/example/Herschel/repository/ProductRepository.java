package com.example.Herschel.repository;

import com.example.Herschel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findAllByProductType(String productType);
    List<Product> findAllByProductTypeAndColor(String productType, String color);
    List<Product> findAllByProductTypeAndModel(String productType, String model);
    List<Product> findAllByProductTypeAndYear(String productType, int year);
    List<Product> findAllByProductTypeAndPriceBetween(String productType, int priceLow, int priceHigh);
}
