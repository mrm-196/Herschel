package com.example.Herschel.repository;

import com.example.Herschel.model.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Long>
{
    List<TransactionProduct> findAllByTransactionId(Long transactionId);
    List<TransactionProduct> findAllByProductId(Long productId);
}
