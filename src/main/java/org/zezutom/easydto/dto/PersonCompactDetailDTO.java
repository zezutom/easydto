package org.zezutom.easydto.dto;

/**
 *
 * @author tomasz
 */
public class PersonCompactDetailDTO extends PersonDTO {
    
    private String contactAddress;

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }    
}
