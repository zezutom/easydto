package org.zezutom.easydto.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import org.zezutom.easydto.model.Person;

@Service
public class JpaAddressBook implements AddressBook {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person find(Long id) {
        return (Person) em.createNamedQuery(Person.PERSON_WITH_ADDRESSES).setParameter(1, id).getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        return em.createNamedQuery(Person.ALL_PEOPLE).getResultList();
    }

    @Override
    public Long save(Person person) {
        if (person.getId() == null) {
            em.persist(person);
        } else {
            person = em.merge(person);
        }
        return person.getId();
    }
     
}
