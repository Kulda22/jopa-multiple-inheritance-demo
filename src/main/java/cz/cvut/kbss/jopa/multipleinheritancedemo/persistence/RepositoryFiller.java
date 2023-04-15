package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.AudioBookService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.CopierService;
import cz.cvut.kbss.jopa.multipleinheritancedemo.util.InstanceGenerator;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RepositoryFiller implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(RepositoryFiller.class);
    private AudioBookService audioBookService;
    private CopierService copierService;

    private EntityManager entityManager;

    private Repository repository;

    @Autowired
    public RepositoryFiller(AudioBookService audioBookService, CopierService copierService, EntityManager entityManager, Repository repository) {
        this.audioBookService = audioBookService;
        this.copierService = copierService;
        this.entityManager = entityManager;
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.error("Starting listener");
        if (true) {

//            final Repository repo = entityManager.unwrap(Repository.class); /// fails hard
            try (final RepositoryConnection conn = repository.getConnection()) {
                conn.begin();
                conn.add(RepositoryFiller.class.getClassLoader().getResourceAsStream("ontologies/schema.ttl"), Vocabulary.URI_BASE, RDFFormat.TURTLE);
                conn.commit();
            } catch (IOException e) {
                throw new RuntimeException("Unable to load TermIt model for import.", e);
            }
        }

        LOG.error("Filling repository with dummy data");

        InstanceGenerator instanceGenerator = new InstanceGenerator();

        for (int i = 0; i < 5; i++) {
            audioBookService.persist(instanceGenerator.generateAudioBook());
            copierService.persist(instanceGenerator.generateCopier());
        }
    }
}
