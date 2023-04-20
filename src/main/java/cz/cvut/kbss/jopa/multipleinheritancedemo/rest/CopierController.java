package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Copier;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.BadRequestException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.NotFoundException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.RestUtils;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.CopierService;
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
@RequestMapping("/copiers")
public class CopierController {

    private static final Logger LOG = LoggerFactory.getLogger(CopierController.class);

    private final CopierService copierService;

    @Autowired
    public CopierController(CopierService copierService) {
        this.copierService = copierService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Copier> getCopiers() {
        return copierService.findAll();
    }

    @GetMapping(value = "/{key}")
    public Copier getCopierByKey(@PathVariable("key") String key) {
        final Copier s = copierService.findByKey(key);
        if (s == null) {
            throw new NotFoundException("Copier with key " + key + " not found.");
        }
        return s;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopier(@RequestBody Copier copier) {
        copierService.persist(copier);
        LOG.debug("Copier successfully created.");
        final HttpHeaders headers = RestUtils.createLocationHeader("/{key}", copier.getKey());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{key}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCopier(@PathVariable("key") String key,@RequestBody Copier copier) {
        if (!key.equals(copier.getKey())) {
            throw new BadRequestException("Key in path and in entity mismatched");
        }
        copierService.update(copier);
        LOG.debug("Copier successfully updated.");
        final HttpHeaders headers = RestUtils.createLocationHeader("/{key}", copier.getKey());
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCopier(@PathVariable("key") String key) {
        final Copier s = copierService.findByKey(key);
        if (s == null) {
            throw new NotFoundException("Copier with key " + key + " not found.");
        }
        copierService.delete(s);
        LOG.debug("Copier {} was successfully deleted.", s);
    }
}
