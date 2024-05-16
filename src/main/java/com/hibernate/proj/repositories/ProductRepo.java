package com.hibernate.proj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.proj.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
