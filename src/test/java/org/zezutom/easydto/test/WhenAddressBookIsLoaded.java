package org.zezutom.easydto.test;

import java.util.List;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.easydto.dto.AddressDTO;
import org.zezutom.easydto.dto.PersonCompactDetailDTO;
import org.zezutom.easydto.dto.PersonDTO;
import org.zezutom.easydto.dto.PersonDetailDTO;
import org.zezutom.easydto.service.PersonDetailBuilder;
import org.zezutom.easydto.service.PresentationManager;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Transactional
@TransactionConfiguration(defaultRollback=true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appbook-context.xml")
public class WhenAddressBookIsLoaded {

    @Resource
    private PresentationManager manager;
    
    @Test
    public void peopleShouldBeListedInAlphabeticalOrder() {
        List<PersonDTO> people = manager.getPeople();
        assertNotNull(people);        
        assertThat(people.size(), is(3));
        assertPerson(people.get(0), "Mary", "Adams");
        assertPerson(people.get(1), "John", "Doe");
        assertPerson(people.get(2), "Peter", "Smith");
    }    
    
    @Test
    public void addressListShouldBeAvailableWithPersonDetails() {        
        PersonDetailDTO detail = manager.getPersonDetail(1l);        
        assertNotNull(detail);

        // Personal address(es) should be loaded as a part of personal details
        List<AddressDTO> addresses = detail.getAddresses();
        assertNotNull(addresses);
        assertThat(addresses.size(), is(3));
        assertAddress(addresses.get(0), "Street One", "London", "OWAFEWFEW", true);
        assertAddress(addresses.get(1), "Street Two", "Birmingham", "AFILWFEWE");
        assertAddress(addresses.get(2), "Street Three", "Liverpool", "WAFEIWEWE");
    }
   
    @Test
    public void theContactAddressShouldBeAvailableWhenCompactDetailsAreRequested() {
        PersonCompactDetailDTO detail = manager.getPersonDetailAsCompact(1l);
        assertPerson(detail, "John", "Doe");
        assertEquals("Street One, London, OWAFEWFEW", detail.getContactAddress());
    }
    
    @Test
    public void itShouldBePossibleToAmendPersonalDetails() {
        PersonDetailDTO person = manager.getPersonDetail(1l);
        person.setFirstName("Jimmy");
        
        Long id = manager.savePerson(person);        
        assertThat(id, is(1l));
        assertPerson(manager.getPersonDetail(id), "Jimmy", "Doe");
    }
    
    @Test
    public void itShouldBePossibleToAddANewPerson() {
        PersonDetailDTO detail = PersonDetailBuilder.get()
                                    .setDetails("Brand", "Newman", "brand.new@mymail.com")
                                    .addAddress("Docklands 1", "Liverpool", "EFWEER", false)
                                    .addAddress("High Street 15", "Killkenny", "KIL005", true)
                                    .build();
        Long id = manager.savePerson(detail);        
        assertNotNull(id);
        
        detail = manager.getPersonDetail(id);
        assertPerson(detail, "Brand", "Newman");
        
        List<AddressDTO> addresses = detail.getAddresses();        
        assertNotNull(addresses);
        assertThat(addresses.size(), is(2));
        assertAddress(addresses.get(0), "Docklands 1", "Liverpool", "EFWEER");
        assertAddress(addresses.get(1), "High Street 15", "Killkenny", "KIL005", true);
        
    }
    
    private void assertPerson(PersonDTO person, String firstName, String lastName) {
        assertNotNull(person);
        assertTrue(firstName.equals(person.getFirstName()));
        assertTrue(lastName.equals(person.getLastName()));
    }
    
    private void assertAddress(AddressDTO address, String street, String city, String zipCode) {
        assertAddress(address, street, city, zipCode, false);
    }
    
    private void assertAddress(AddressDTO address, String street, String city, String zipCode, boolean contact) {
        assertNotNull(address);
        assertTrue(street.equals(address.getStreet()));
        assertTrue(city.equals(address.getCity()));
        assertTrue(zipCode.equals(address.getZipCode()));
        assertThat(address.isContact(), is(contact));
    }
}
