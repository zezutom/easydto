package org.zezutom.easydto.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.easydto.dto.PersonCompactDetailDTO;
import org.zezutom.easydto.dto.PersonDTO;
import org.zezutom.easydto.dto.PersonDetailDTO;
import org.zezutom.easydto.model.Address;
import org.zezutom.easydto.model.Person;

/**
 * Makes use of the Orika mapper.
 * @see http://code.google.com/p/orika/
 * 
 * @author tomasz
 */
@Service
public class OrikaPresentationManager implements PresentationManager {

    public static final String NO_CONTACT_ADDRESS = "No contact address provided.";
    
    @Resource
    private AddressBook addressBook;
    
    private MapperFactory factory = new DefaultMapperFactory.Builder().build();

    public OrikaPresentationManager() {
        // Register any custom settings     
        ObjectFactory<PersonCompactDetailDTO> customFactory = new ObjectFactory<PersonCompactDetailDTO>() {
                        
            @Override
            public PersonCompactDetailDTO create(Object o, MappingContext mc) {
                PersonCompactDetailDTO detail = new PersonCompactDetailDTO();
                Address address = ((Person) o).getContactAddress();
                
                if (address != null) {
                    detail.setContactAddress(address.toString());
                }
                return detail;
            }            
        };                
        factory.registerObjectFactory(customFactory, PersonCompactDetailDTO.class);        
    }

    @Override
    public List<PersonDTO> getPeople() {
        List<PersonDTO> people = new ArrayList<PersonDTO>();

        for (Person person : addressBook.findAll()) {
            people.add(map(person, PersonDTO.class));
        }

        return people;
    }

    @Override
    public PersonDetailDTO getPersonDetail(Long id) {
        return map(addressBook.find(id), PersonDetailDTO.class);
    }

    @Override
    public PersonCompactDetailDTO getPersonDetailAsCompact(Long id) {
        return map(addressBook.find(id), PersonCompactDetailDTO.class);
    }

    @Transactional
    @Override
    public Long savePerson(PersonDetailDTO person) {
        return addressBook.save(map(person, Person.class));
    }
    
    private <T extends Object> T map(Object source, Class<T> clazz) {
        return factory.getMapperFacade().map(source, clazz);
    }
}
