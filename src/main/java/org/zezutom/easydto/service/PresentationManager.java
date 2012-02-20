package org.zezutom.easydto.service;

import java.util.List;
import org.zezutom.easydto.dto.PersonCompactDetailDTO;
import org.zezutom.easydto.dto.PersonDTO;
import org.zezutom.easydto.dto.PersonDetailDTO;

/**
 * Provides data adjusted to presentation needs.
 * 
 * @author tomasz
 */
public interface PresentationManager {
    
    List<PersonDTO> getPeople();
    
    PersonDetailDTO getPersonDetail(Long id);
    
    PersonCompactDetailDTO getPersonDetailAsCompact(Long id);
    
    Long savePerson(PersonDetailDTO person);
}
