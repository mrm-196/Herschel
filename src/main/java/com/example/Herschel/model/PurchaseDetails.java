package com.example.Herschel.model;

import java.util.List;
import java.util.Map;

public class PurchaseDetails
{
    private Long userId;
    private Map<Long, Long> products; // Entry Format -> (ID, RequiredNumber)

    public PurchaseDetails(Long userId, Map<Long, Long> products) {
        this.userId = userId;
        this.products = products;
    }

    public PurchaseDetails() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Long> products) {
        this.products = products;
    }
}
