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

    private final EntityManager em;

    @Autowired
    public BookDao(EntityManager em) {
        this.em = em;
    }

    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public Book findByISBN(String ISBN) {
        try {
            return em.createNamedQuery("Book.findByISBN", Book.class).setParameter("ISBN", ISBN)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.warn("Book with key {} not found.", ISBN);
            return null;
        }
    }

    public void delete(Book book) {
        assert book != null;

        final Book toRemove = em.merge(book);
        em.remove(toRemove);

        LOG.info("Book {} deleted.", book);
    }
}
