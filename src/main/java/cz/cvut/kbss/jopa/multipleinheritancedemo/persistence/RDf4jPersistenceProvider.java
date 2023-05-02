package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.config.RepositoryConfigException;
import org.eclipse.rdf4j.repository.manager.RepositoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.URI;

@Configuration
@PropertySource("classpath:application.properties")
public class RDf4jPersistenceProvider {

    private static final Logger LOG = LoggerFactory.getLogger(RDf4jPersistenceProvider.class);
    private static final String URL_PROPERTY = "repositoryUrl";

    @Autowired
    private Environment environment;

    @Autowired
    private EntityManagerFactory emf;

    private Repository repository;

    @Bean
    public Repository repository() {
        return repository;
    }

    @PostConstruct
    private void initializeStorage() {
        forceRepoInitialization();
        final String repoUrl = environment.getRequiredProperty(URL_PROPERTY);
        try {
            this.repository = RepositoryProvider.getRepository(repoUrl);
            assert repository.isInitialized();
        } catch (RepositoryException | RepositoryConfigException e) {
            LOG.error("Unable to connect to RDF4J repository at " + repoUrl, e);
        }
    }

    /**
     * Force JOPA to initialize the storage so that we don't have to initialize it ourselves.
     * <p>
     * If we were to initialize the storage, we would have to create appropriate {@link
     * org.eclipse.rdf4j.repository.config.RepositoryConfig} for the repo, so we rather let JOPA do it for us.
     */
    private void forceRepoInitialization() {
        final EntityManager em = emf.createEntityManager();
        try {
            // The URI doesn't matter, we just need to trigger repository connection initialization
            em.find(AudioBook.class, URI.create("http://unknown"));
        } finally {
            em.close();
        }
    }
}

