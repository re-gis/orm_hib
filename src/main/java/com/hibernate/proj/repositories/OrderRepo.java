package com.hibernate.proj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.proj.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    
}
