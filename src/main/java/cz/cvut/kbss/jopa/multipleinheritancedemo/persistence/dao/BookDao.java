package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.exceptions.NoResultException;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {
    private static final Logger LOG = LoggerFactory.getLogger(BookDao.class);

    // Notice that we are using Autowired instead of PersistenceContext, which is tightly coupled with traditional JPA
    private final EntityManager em;

    @Autowired
    public BookDao(EntityManager em) {
        this.em = em;
    }

    public List<Book> findAll() {
//        return em.createQuery("SELECT b FROM AudioBook b", Book.class).getResultList();

        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public Book findByISNB(String ISNB) {
        try {
            return em.createNamedQuery("Book.findByISNB", Book.class).setParameter("ISNB", ISNB)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.warn("Book with key {} not found.", ISNB);
            return null;
        }
    }
    public void persist(Book book) {
        assert book != null;
        assert book.getUri() != null;
        em.persist(book);
        LOG.info("Book {} persisted.", book);
    }

    public void delete(Book book) {
        assert book != null;

        final Book toRemove = em.merge(book);
        em.remove(toRemove);

        LOG.info("Book {} deleted.", book);
    }
}
