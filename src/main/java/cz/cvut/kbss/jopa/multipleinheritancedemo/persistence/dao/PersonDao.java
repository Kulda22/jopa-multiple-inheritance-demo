package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URI;

@Repository
public class PersonDao {
    private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);

    private final EntityManager em;

    @Autowired
    public PersonDao(EntityManager em) {
        this.em = em;
    }


    public Person find(URI uri) {
        return em.find(Person.class, uri);
    }

    public void persist(Person person) {
        assert person != null;
        assert person.getUri() != null;
        em.persist(person);
        LOG.info("Person {} persisted.", person);
    }

    public Person update(Person person) {
        assert person != null;

        final Person updated = em.merge(person);

        LOG.info("Person {} updated.", updated);

        return updated;
    }

    public void delete(Person person) {
        assert person != null;

        final Person toRemove = em.merge(person);
        em.remove(toRemove);

        LOG.info("Person {} deleted.", toRemove);
    }


}
