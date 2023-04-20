package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Book;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.NotFoundException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.RestUtils;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{ISBN}")
    public Book getBook(@PathVariable("ISBN") String ISBN) {
        final Book s = bookService.findByISBN(ISBN);
        if (s == null) {
            throw new NotFoundException("Book with ISBN " + ISBN + " not found.");
        }
        return s;
    }

    @DeleteMapping(value = "/{ISBN}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("ISBN") String ISBN) {
        final Book s = bookService.findByISBN(ISBN);
        if (s == null) {
            throw new NotFoundException("Book with key " + ISBN + " not found.");
        }
        bookService.delete(s);
        LOG.debug("Book {} was successfully deleted.", s);
    }
}
