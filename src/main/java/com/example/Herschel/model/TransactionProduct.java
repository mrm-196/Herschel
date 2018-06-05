package com.example.Herschel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_product")
public class TransactionProduct
{
    @Id
    @GeneratedValue
    private Long id;

    private Long transactionId;
    private Long productId;
    private Long productAmount;

    public TransactionProduct()
    {

    }

    public TransactionProduct(Long transactionId, Long productId, Long productAmount) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.productAmount = productAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }
}
