package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Book;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional
    public Book findByISBN(String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            return null;
        }
        return bookDao.findByISBN(ISBN);
    }

    @Transactional
    public void persist(Book book) {
        Objects.requireNonNull(book);
        Objects.requireNonNull(book.getUri());

        bookDao.persist(book);
    }

    public void delete(Book book) {
        Objects.requireNonNull(book);
        bookDao.delete(book);
    }
}