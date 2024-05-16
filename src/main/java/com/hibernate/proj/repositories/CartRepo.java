package com.hibernate.proj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.proj.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

}
