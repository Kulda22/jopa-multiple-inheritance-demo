package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Printer;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.PrinterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/printers")
public class PrinterController {

    private static final Logger LOG = LoggerFactory.getLogger(PrinterController.class);

    private final PrinterService printerService;

    @Autowired
    public PrinterController(PrinterService printerService) {
        this.printerService = printerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Printer> getPrinters() {
        return printerService.findAll();
    }

}
