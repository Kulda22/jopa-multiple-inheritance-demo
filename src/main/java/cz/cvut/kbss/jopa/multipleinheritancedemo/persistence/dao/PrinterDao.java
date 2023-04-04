package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Printer;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrinterDao {
    private static final Logger LOG = LoggerFactory.getLogger(PrinterDao.class);

    private final EntityManager em;

    @Autowired
    public PrinterDao(EntityManager em) {
        this.em = em;
    }

    public List<Printer> findAll() {
        return em.createNamedQuery("Printer.findAll", Printer.class).getResultList();
    }

}
