package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Device;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Scanner;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.DeviceDao;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.ScannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScannerService {
    private final ScannerDao scannerDao;

    @Autowired
    public ScannerService(ScannerDao scannerDao) {
        this.scannerDao = scannerDao;
    }

    @Transactional
    public List<Scanner> findAll() {
        return scannerDao.findAll();
    }
}