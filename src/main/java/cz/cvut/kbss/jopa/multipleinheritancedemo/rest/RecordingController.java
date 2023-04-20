package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Recording;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.NotFoundException;
import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util.RestUtils;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.RecordingService;
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
@RequestMapping("/recordings")
public class RecordingController {

    private static final Logger LOG = LoggerFactory.getLogger(RecordingController.class);

    private final RecordingService recordingService;

    @Autowired
    public RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recording> getRecordings() {
        return recordingService.findAll();
    }

    @GetMapping(value = "/{uri}")
    public Recording getRecording(@PathVariable("uri") String uri) {
        final Recording s = recordingService.find(uri);
        if (s == null) {
            throw new NotFoundException("Recording with uri " + uri + " not found.");
        }
        return s;
    }

    @DeleteMapping(value = "/{uri}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecording(@PathVariable("uri") String uri) {
        final Recording s = recordingService.find(uri);
        if (s == null) {
            throw new NotFoundException("Recording with key " + uri + " not found.");
        }
        recordingService.delete(s);
        LOG.debug("Recording {} was successfully deleted.", s);
    }
}
