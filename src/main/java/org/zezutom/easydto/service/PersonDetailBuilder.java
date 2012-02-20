package org.zezutom.easydto.service;

import java.util.ArrayList;
import java.util.List;
import org.zezutom.easydto.dto.AddressDTO;
import org.zezutom.easydto.dto.PersonDetailDTO;

/**
 * 
 * @author tomasz
 */
public class PersonDetailBuilder {

    private PersonDetailDTO detail = new PersonDetailDTO();
    
    private PersonDetailBuilder() {}
        
    public PersonDetailBuilder setDetails(String firstName, String lastName, String email) {
        detail.setFirstName(firstName);
        detail.setLastName(lastName);
        detail.setEmail(email);
        
        return this;
    }
    
    public PersonDetailBuilder addAddress(String street, String city, String zipCode, boolean contact) {
        AddressDTO address = new AddressDTO();
        
        address.setStreet(street);
        address.setCity(city);        
        address.setZipCode(zipCode);
        address.setContact(contact);
        
        List<AddressDTO> addresses = detail.getAddresses();
        
        if (addresses == null) {
            addresses = new ArrayList<AddressDTO>();
            detail.setAddresses(addresses);
        }
        addresses.add(address);
        
        return this;
    }
    
    public PersonDetailDTO build() {
        return detail;
    }
    
    public static PersonDetailBuilder get() {
        return new PersonDetailBuilder();
    }
}
