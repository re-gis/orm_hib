package com.hibernate.proj.models;

import com.hibernate.proj.classes.Address;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_owner_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_cart_id")
    private Cart cart;

    @NotNull(message = "Please provide the total price!")
    @Column(length = 255)
    private int totalPrice;

    @Embedded
     @AttributeOverrides({
            @AttributeOverride(name = "district", column = @Column(name = "district")),
            @AttributeOverride(name = "sector", column = @Column(name = "sector")),
            @AttributeOverride(name = "street", column = @Column(name = "street"))
    })
    private Address address;

    @Column(nullable = false)
    private boolean delivered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

}
