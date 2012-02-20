package org.zezutom.easydto.dto;

import java.util.List;

/**
 *
 * @author tomasz
 */
public class PersonDetailDTO extends PersonDTO {

    private List<AddressDTO> addresses;

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }        
}
