package org.zezutom.easydto.dto;

import java.io.Serializable;

/**
 *
 * @author tomasz
 */
public class AddressDTO implements Serializable {

    private Long id;
    
    private String street;
    
    private String city;
    
    private String zipCode;
    
    private boolean contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
