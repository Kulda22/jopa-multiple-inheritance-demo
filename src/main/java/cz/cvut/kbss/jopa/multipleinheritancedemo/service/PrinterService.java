package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Printer;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Scanner;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.PrinterDao;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.ScannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrinterService {
    private final PrinterDao printerDao;

    @Autowired
    public PrinterService(PrinterDao printerDao) {
        this.printerDao = printerDao;
    }

    @Transactional
    public List<Printer> findAll() {
        return printerDao.findAll();
    }
}