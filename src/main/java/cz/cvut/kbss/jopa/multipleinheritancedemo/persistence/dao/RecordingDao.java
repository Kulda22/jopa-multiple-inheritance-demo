package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Recording;
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
    /// if we have for example uri getter / setter on entity, we can find or delete it easily
    public Recording find(String uri) {
        return em.find(Recording.class, uri);
    }

    public void delete(Recording recording) {
        assert recording != null;

        final Recording toRemove = em.merge(recording);
        em.remove(toRemove);

        LOG.info("Recording {} deleted.", recording);
    }
}
