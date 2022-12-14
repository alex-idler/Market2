package com.example.market2.repository;

import com.example.market2.entity.Product;
import com.example.market2.entity.User;
import com.example.market2.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUser(User user);
    List<Purchase> findByProduct(Product product);
}
