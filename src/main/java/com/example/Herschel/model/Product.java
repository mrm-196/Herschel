package com.example.Herschel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue
    private Long id;

    private String productType;
    private String model;
    private Long year;
    private String color;
    private Long price;
    private Long available;

    public Product(String productType, String model, Long year, String color, Long price, Long available) {
        this.productType = productType;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.available = available;
    }

    public Product()
    {

    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
