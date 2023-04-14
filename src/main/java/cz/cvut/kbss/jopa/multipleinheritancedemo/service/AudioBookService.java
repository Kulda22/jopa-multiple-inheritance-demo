package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Recording;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.AudioBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AudioBookService {
    private final AudioBookDao audioBookDao;

    @Autowired
    public AudioBookService(AudioBookDao audioBookDao) {
        this.audioBookDao = audioBookDao;
    }

    @Transactional
    public List<AudioBook> findAll() {
        return audioBookDao.findAll();
    }

    @Transactional
    public AudioBook findByISNB(String ISNB) {
        if (ISNB == null || ISNB.isEmpty()) {
            return null;
        }
        return audioBookDao.findByISNB(ISNB);
    }

    @Transactional
    public Recording find(String uri) {
        if (uri == null || uri.isEmpty()) {
            return null;
        }
        return audioBookDao.find(uri);
    }

    @Transactional
    public void persist(AudioBook audioBook) {
        Objects.requireNonNull(audioBook);

        audioBook.generateUri();
        audioBookDao.persist(audioBook);
    }

    @Transactional
    public void delete(AudioBook baudioBookok) {
        Objects.requireNonNull(baudioBookok);
        audioBookDao.delete(baudioBookok);
    }
}