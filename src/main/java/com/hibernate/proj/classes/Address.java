package com.hibernate.proj.classes;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String district;
    private String sector;
    private String street;
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
}
