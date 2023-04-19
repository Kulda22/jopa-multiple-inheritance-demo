package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Person;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Recording;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.AudioBookDao;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AudioBookService {
    private final AudioBookDao audioBookDao;
    private final PersonDao personDao;

    @Autowired
    public AudioBookService(AudioBookDao audioBookDao, PersonDao personDao) {
        this.audioBookDao = audioBookDao;
        this.personDao = personDao;
    }

    @Transactional
    public List<AudioBook> findAll() {
        return audioBookDao.findAll();
    }

    @Transactional
    public AudioBook findByISBN(String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            return null;
        }
        return audioBookDao.findByISBN(ISBN);
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

        if (audioBook.getInterpret() != null) {
            audioBook.getInterpret().generateUri();
            Person saved = personDao.find(audioBook.getInterpret().getUri());
            if (saved == null) {
                personDao.persist(audioBook.getInterpret());
            }
        }
        if (audioBook.getAuthor() != null) {
            audioBook.getAuthor().generateUri();
            Person saved = personDao.find(audioBook.getAuthor().getUri());
            if (saved == null) {
                personDao.persist(audioBook.getAuthor());
            }
        }

        audioBookDao.persist(audioBook);
    }

    @Transactional
    public void update(AudioBook audioBook) {
        Objects.requireNonNull(audioBook);


        if (audioBook.getInterpret() != null) {
            audioBook.getInterpret().generateUri();
            Person saved = personDao.find(audioBook.getInterpret().getUri());
            if (saved == null) {
                personDao.persist(audioBook.getInterpret());
            } else if (!audioBook.getInterpret().equals(saved)) {
                personDao.update(audioBook.getInterpret());
            }
        }

        if (audioBook.getAuthor() != null) {
            audioBook.getAuthor().generateUri();
            Person saved = personDao.find(audioBook.getAuthor().getUri());
            if (saved == null) {
                personDao.persist(audioBook.getAuthor());
            } else if (!audioBook.getAuthor().equals(saved)) {
                personDao.update(audioBook.getAuthor());
            }
        }

         audioBookDao.update(audioBook);
    }

    @Transactional
    public void delete(AudioBook audioBook) {
        Objects.requireNonNull(audioBook);
        audioBookDao.delete(audioBook);
    }


}