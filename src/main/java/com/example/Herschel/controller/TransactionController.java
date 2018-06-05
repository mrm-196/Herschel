package com.example.Herschel.controller;

import com.example.Herschel.exception.ResourceNotFoundException;
import com.example.Herschel.model.*;
import com.example.Herschel.repository.ProductRepository;
import com.example.Herschel.repository.TransactionProductRepository;
import com.example.Herschel.repository.TransactionRepository;
import com.example.Herschel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/transactions")
public class TransactionController
{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionProductRepository transactionProductRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{id}")
    public TransactionDetails getTransactionDetails(@PathVariable(value = "id") Long id)
    {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id));
        Long userId = transaction.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        String username = user.getUsername();
        Boolean successStatus = transaction.getSuccess();
        List<TransactionProduct> transProdList = transactionProductRepository.findAllByTransactionId(id);
        List<Product> productList = new ArrayList<>();

        for (TransactionProduct transactionProduct : transProdList)
        {
            Long productId = transactionProduct.getProductId();
            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            productList.add(product);
        }

        TransactionDetails result = new TransactionDetails(transaction.getId(), username, productList, successStatus);
        return result;
    }

    @GetMapping("/purchase")
    public boolean purchase(@RequestBody PurchaseDetails details)
    {
        if(checkProductList(details.getProducts()))
            return false;
        for (Map.Entry<Long, Long> entry : details.getProducts().entrySet())
        {
            Long productId = entry.getKey();
            Long required = entry.getValue();
            if(!checkProduct(productId, required))
                return false;
        }

        Long transactionID = initiateTransaction(details.getUserId()).getId();

        for (Map.Entry<Long, Long> entry : details.getProducts().entrySet())
        {
            buyProduct(transactionID, entry);
        }
        return true;
    }

    private boolean checkProductList(@RequestBody Map<Long, Long> productEntries)
    {
        try {
            for (Map.Entry<Long, Long> entry : productEntries.entrySet())
            {
                Long productId = entry.getKey();
                Long required = entry.getValue();
                if(!checkProduct(productId, required))
                    return false;
            }
        }
        catch (ResourceNotFoundException exception)
        {
            return false;
        }
        return true;
    }

    private boolean checkProduct(Long productId, Long requiredNumber)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        if(product.getAvailable() >= requiredNumber)
            return true;
        return false;
    }

    private boolean buyProduct(Long transactionId, Map.Entry<Long, Long> purchaseEntry)
    {
        Long productId = purchaseEntry.getKey();
        Long required = purchaseEntry.getValue();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        TransactionProduct transactionProduct = new TransactionProduct(transactionId, productId, required);
        Long avail = product.getAvailable() - required;
        product.setAvailable(avail);
        productRepository.save(product);
        transactionProductRepository.save(transactionProduct);
        return true;
    }

    private Transaction initiateTransaction(Long userId)
    {
        Transaction transaction = new Transaction(userId, true);
        return transactionRepository.save(transaction);
    }
}
