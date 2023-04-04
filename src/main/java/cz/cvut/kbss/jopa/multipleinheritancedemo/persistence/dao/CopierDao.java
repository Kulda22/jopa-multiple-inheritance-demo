package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Copier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CopierDao {
    private static final Logger LOG = LoggerFactory.getLogger(CopierDao.class);

    private final EntityManager em;

    @Autowired
    public CopierDao(EntityManager em) {
        this.em = em;
    }

    public List<Copier> findAll() {
        return em.createNamedQuery("Copier.findAll", Copier.class).getResultList();
    }

    public Copier findByKey(String key) {
        return em.createNamedQuery("Copier.findByKey", Copier.class).getSingleResult();
    }

    public void persist(Copier Copier) {
        assert Copier != null;
        assert Copier.getUri() != null;
        em.persist(Copier);
        LOG.info("Copier {} persisted.", Copier);
    }

    public void delete(Copier copier) {
        assert copier != null;

        final Copier toRemove = em.merge(copier);
        em.remove(toRemove);

        LOG.info("Copier {} deleted.", copier);
    }
}
