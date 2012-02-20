package org.zezutom.easydto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "people")
@Entity
@NamedQueries({
    @NamedQuery(name=Person.PERSON_WITH_ADDRESSES, query="select distinct p from Person p left join fetch p.addresses where p.id = ?"),
    @NamedQuery(name=Person.ALL_PEOPLE, query="select p from Person p order by p.lastName, p.firstName")    
})
public class Person implements Serializable {

    public static final String PERSON_WITH_ADDRESSES = "Person.PERSON_WITH_ADDRESSES";
    
    public static final String ALL_PEOPLE = "Person.ALL_PEOPLE";
    
    public static final String DISPLAY_FORMAT = "%s %s (%s)";
    
    private static final long serialVersionUID = -869313365640539096L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable=false)
    private String firstName;
    
    @Column(name = "last_name", nullable=false)
    private String lastName;
    
    @Column(nullable=false, unique=true)
    private String email;
    
    @OneToMany(mappedBy = "person")
    private Collection<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }
    
    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new ArrayList<Address>();
        }
        addresses.add(address);
    }
    
    public Address getContactAddress() {
        if (addresses == null || addresses.isEmpty()) {
            return null;
        }
        
        Address contactAddress = null;
        
        for (Address address : addresses) {
            if (address.isContact()) {
                contactAddress = address;
                break;
            }
        }
        return contactAddress;
        
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 17 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 17 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return String.format(DISPLAY_FORMAT, firstName, lastName, email);
    }
    
    
}
