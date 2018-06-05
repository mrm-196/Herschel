package com.example.Herschel.model;

import java.util.List;

public class TransactionDetails
{
    private Long transactionId;
    private String username;
    private List<Product> productList;
    private Boolean success;
    private Long price;

    public TransactionDetails(Long transactionId, String username, List<Product> productList, Boolean success) {
        this.transactionId = transactionId;
        this.username = username;
        this.productList = productList;
        this.success = success;
        setPrice();
    }

    public TransactionDetails()
    {

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getUsername() {
        return username;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Long getPrice() {
        return price;
    }

    private void setPrice()
    {
        price = Long.valueOf(0);
        for (Product product : productList)
            price += product.getPrice();
    }
}
