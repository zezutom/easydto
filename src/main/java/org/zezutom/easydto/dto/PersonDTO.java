package org.zezutom.easydto.dto;

import java.io.Serializable;

/**
 *
 * @author tomasz
 */
public class PersonDTO implements Serializable {

    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
        
}
