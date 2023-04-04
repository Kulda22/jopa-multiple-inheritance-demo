package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Recording;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Copier;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.CopierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CopierService {
    private final CopierDao copierDao;

    @Autowired
    public CopierService(CopierDao copierDao) {
        this.copierDao = copierDao;
    }

    @Transactional
    public List<Copier> findAll() {
        return copierDao.findAll();
    }

    @Transactional
    public Copier findByKey(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        return copierDao.findByKey(key);
    }

    @Transactional
    public void persist(Copier copier) {
        Objects.requireNonNull(copier);

        copier.generateUri();
        copierDao.persist(copier);
    }

    @Transactional
    public void delete(Copier copier) {
        Objects.requireNonNull(copier);
        copierDao.delete(copier);
    }
}