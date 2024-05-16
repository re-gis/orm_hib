package com.hibernate.proj.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Contacts {
    @Column(unique = true)
    @NotNull(message = "Please provide the phone number!")
    private String phone;

    @Column(unique = true)
    @NotBlank(message = "Email must be provided!")
    @Email(message = "Email must be valid!")
    private String email;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
