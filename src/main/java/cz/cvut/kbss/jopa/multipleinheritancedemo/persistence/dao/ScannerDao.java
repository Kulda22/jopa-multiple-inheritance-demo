package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Device;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScannerDao {
    private static final Logger LOG = LoggerFactory.getLogger(ScannerDao.class);

    private final EntityManager em;

    @Autowired
    public ScannerDao(EntityManager em) {
        this.em = em;
    }

    public List<Scanner> findAll() {
        return em.createNamedQuery("Scanner.findAll", Scanner.class).getResultList();
    }

}
