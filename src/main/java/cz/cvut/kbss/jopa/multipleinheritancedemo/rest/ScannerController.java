package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Printer;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Scanner;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.PrinterService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.ScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/scanners")
public class ScannerController {

    private static final Logger LOG = LoggerFactory.getLogger(ScannerController.class);

    private final ScannerService scannerService;

    @Autowired
    public ScannerController(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Scanner> getScanners() {
        return scannerService.findAll();
    }

}
