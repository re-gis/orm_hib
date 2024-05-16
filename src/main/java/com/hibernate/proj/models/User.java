package com.hibernate.proj.models;

import java.util.*;

import com.hibernate.proj.classes.Contacts;
import com.hibernate.proj.enums.URole;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "phone"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The firstname is required!")
    @Column(name = "first_name", length = 255)
    private String fname;

    @NotBlank(message = "The lastname is required!")
    @Column(name = "last_name", length = 255)
    private String lname;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    @OneToMany(mappedBy = "user")
    private List<Product> my_products = new ArrayList<Product>();

    public List<Product> getMy_products() {
        return my_products;
    }

    public void setMy_products(List<Product> my_products) {
        this.my_products = my_products;
    }

    @NotNull(message = "User role is required!")
    @Enumerated(EnumType.STRING)
    private URole role;

    public URole getRole() {
        return role;
    }

    public void setRole(URole role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "phone", column = @Column(name = "phone"))
    })
    private Contacts contacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString(){
        return String.format("User [\n\tFirst Name: %s\n\tLast Name: %s\n\tnemail: %s\n\tphone: %s\n\trole: %s\n]", this.fname, this.lname, this.contacts.getEmail(), this.contacts.getPhone(), this.role.toString());
    }

}
