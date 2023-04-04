package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence;

import cz.cvut.kbss.jopa.multipleinheritancedemo.service.AudioBookService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.BookService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.CopierService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.util.InstanceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFiller implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(RepositoryFiller.class);
    private BookService bookService;
    private AudioBookService audioBookService;
    private CopierService copierService;

    @Autowired
    public RepositoryFiller(BookService bookService, AudioBookService audioBookService, CopierService copierService) {
        this.bookService = bookService;
        this.audioBookService = audioBookService;
        this.copierService = copierService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.error("Starting listener");
        InstanceGenerator instanceGenerator = new InstanceGenerator();
//        for (int i = 0; i < 5; i++) {
//            bookService.persist(instanceGenerator.createBook());
//        }
        for (int i = 0; i < 5; i++) {
            audioBookService.persist(instanceGenerator.generateAudioBook());
            copierService.persist(instanceGenerator.generateCopier());
        }
    }
}
