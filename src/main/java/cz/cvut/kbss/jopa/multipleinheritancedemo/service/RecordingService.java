package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Recording;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.RecordingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RecordingService {
    private final RecordingDao recordingDao;

    @Autowired
    public RecordingService(RecordingDao recordingDao) {
        this.recordingDao = recordingDao;
    }

    @Transactional
    public List<Recording> findAll() {
        return recordingDao.findAll();
    }

    @Transactional
    public Recording find(String uri) {
        if (uri == null || uri.isEmpty()) {
            return null;
        }
        return recordingDao.find(uri);
    }

    @Transactional
    public void persist(Recording recording) {
        Objects.requireNonNull(recording);

        recording.generateUri();
        recordingDao.persist(recording);
    }

    @Transactional
    public void delete(Recording recording) {
        Objects.requireNonNull(recording);
        recordingDao.delete(recording);
    }
}