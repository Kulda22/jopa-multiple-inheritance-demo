package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.BadRequestException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.NotFoundException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.RestUtils;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.AudioBookService;
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

    @GetMapping(value = "/{ISBN}")
    public AudioBook getAudioBook(@PathVariable("ISBN") String ISBN) {
        final AudioBook s = audioBookService.findByISBN(ISBN);
        if (s == null) {
            throw new NotFoundException("AudioBook with ISBN " + ISBN + " not found.");
        }
        return s;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAudioBook(@RequestBody AudioBook audioBook) {
        audioBookService.persist(audioBook);
        LOG.debug("AudioBook successfully created.");
        final HttpHeaders headers = RestUtils.createLocationHeader("/{ISBN}", audioBook.getISBN());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ISBN}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateAudioBook(@PathVariable("ISBN") String ISBN, @RequestBody AudioBook audioBook) {
        if (!ISBN.equals(audioBook.getISBN())) {
            throw new BadRequestException("ISBN in path and in entity mismatched");
        }
        audioBookService.update(audioBook);
        LOG.debug("AudioBook successfully updated.");
        final HttpHeaders headers = RestUtils.createLocationHeader("/{ISBN}", audioBook.getISBN());
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{ISBN}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAudioBook(@PathVariable("ISBN") String ISBN) {
        final AudioBook s = audioBookService.findByISBN(ISBN);
        if (s == null) {
            throw new NotFoundException("AudioBook with key " + ISBN + " not found.");
        }
        audioBookService.delete(s);
        LOG.debug("AudioBook {} was successfully deleted.", s);
    }
}
