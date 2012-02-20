package org.zezutom.easydto.service;

import java.util.List;
import org.zezutom.easydto.model.Person;


/**
 * Provides personal address details.
 * 
 * @author tomasz
 *
 */
public interface AddressBook {


	/**
	 * Finds a person by id
	 * 	 
	 * @param id
	 * @return	found Person or null
	 */
	Person find(Long id);	
        
        /**
         * Finds all people.
         * 
         * @return people sorted in alphabetical order or an empty list if nothing is found.
         */
        List<Person> findAll();
        
        /***
         * 
         * @param person
         * @return the id of the saved record
         */
        Long save(Person person);
}
