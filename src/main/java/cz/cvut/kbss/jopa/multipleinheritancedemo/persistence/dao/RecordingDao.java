package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordingDao {
    private static final Logger LOG = LoggerFactory.getLogger(RecordingDao.class);

    private final EntityManager em;

    @Autowired
    public RecordingDao(EntityManager em) {
        this.em = em;
    }

    public List<Recording> findAll() {
        return em.createNamedQuery("Recording.findAll", Recording.class).getResultList();
    }

    public Recording find(String uri) {
        return em.find(Recording.class, uri);
    }

    public void persist(Recording recording) {
        assert recording != null;
        assert recording.getUri() != null;
        em.persist(recording);
        LOG.info("Recording {} persisted.", recording);
    }

    public void delete(Recording recording) {
        assert recording != null;

        final Recording toRemove = em.merge(recording);
        em.remove(toRemove);

        LOG.info("Recording {} deleted.", recording);
    }
}
