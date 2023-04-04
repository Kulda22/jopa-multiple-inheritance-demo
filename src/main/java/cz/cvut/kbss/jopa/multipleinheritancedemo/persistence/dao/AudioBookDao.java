package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.exceptions.NoResultException;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AudioBookDao {
    private static final Logger LOG = LoggerFactory.getLogger(AudioBookDao.class);

    // Notice that we are using Autowired instead of PersistenceContext, which is tightly coupled with traditional JPA
    private final EntityManager em;

    @Autowired
    public AudioBookDao(EntityManager em) {
        this.em = em;
    }

    public List<AudioBook> findAll() {
        return em.createNamedQuery("AudioBook.findAll", AudioBook.class).getResultList();
    }

    public AudioBook find(String uri) {
        return em.find(AudioBook.class, uri);
    }

    public AudioBook findByISNB(String ISNB) {
        try {
            return em.createNamedQuery("AudioBook.findByISNB", AudioBook.class).setParameter("ISNB", ISNB).getSingleResult();
        } catch (NoResultException e) {
            LOG.warn("AudioBook with key {} not found.", ISNB);
            return null;
        }
    }

    public void persist(AudioBook audioBook) {
        assert audioBook != null;
        assert audioBook.getUri() != null;
        em.persist(audioBook);
        LOG.info("AudioBook {} persisted.", audioBook);
    }

    public void delete(AudioBook audioBook) {
        assert audioBook != null;

        final AudioBook toRemove = em.merge(audioBook);
        em.remove(toRemove);

        LOG.info("AudioBook {} deleted.", audioBook);
    }
}
