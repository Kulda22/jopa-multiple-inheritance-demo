package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Book;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.NotFoundException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.RestUtils;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.AudioBookService;
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
@RequestMapping("/audio-books")
public class AudioBookController {

    private static final Logger LOG = LoggerFactory.getLogger(AudioBookController.class);

    private final AudioBookService audioBookService;

    @Autowired
    public AudioBookController(AudioBookService audioBookService) {
        this.audioBookService = audioBookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AudioBook> getAudioBooks() {
        return audioBookService.findAll();
    }

    @GetMapping(value = "/{ISNB}")
    public AudioBook getAudioBook(@PathVariable("ISNB") String ISNB) {
        final AudioBook s = audioBookService.findByISNB(ISNB);
        if (s == null) {
            throw new NotFoundException("AudioBook with ISNB " + ISNB + " not found.");
        }
        return s;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody AudioBook audioBook) {
        audioBookService.persist(audioBook);
        LOG.debug("AudioBook successfully created.");
        final HttpHeaders headers = RestUtils.createLocationHeader("/{ISNB}", audioBook.getISNB());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{ISNB}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAudioBook(@PathVariable("ISNB") String ISNB) {
        final AudioBook s = audioBookService.findByISNB(ISNB);
        if (s == null) {
            throw new NotFoundException("AudioBook with key " + ISNB + " not found.");
        }
        audioBookService.delete(s);
        LOG.debug("AudioBook {} was successfully deleted.", s);
    }
}
