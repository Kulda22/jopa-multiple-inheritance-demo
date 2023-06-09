package cz.cvut.kbss.jopa.multipleinheritancedemo;

import cz.cvut.kbss.jopa.Persistence;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProperties;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProvider;
import cz.cvut.kbss.ontodriver.rdf4j.Rdf4jDataSource;
import cz.cvut.kbss.ontodriver.rdf4j.config.Rdf4jOntoDriverProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;


/**
 * Provides entity manager factory as a Spring bean.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PersistenceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(PersistenceFactory.class);

    private static final String URL_PROPERTY = "repositoryUrl";
    private static final String DRIVER_PROPERTY = "driver";

    public static final Map<String, String> PARAMS = initParams();

    @Autowired
    private Environment environment;

    private EntityManagerFactory emf;

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @PostConstruct
    private void init() {
        final Map<String, String> properties = new HashMap<>(PARAMS);
        properties.put(JOPAPersistenceProperties.ONTOLOGY_PHYSICAL_URI_KEY, environment.getProperty(URL_PROPERTY));
        properties.put(JOPAPersistenceProperties.DATA_SOURCE_CLASS, environment.getProperty(DRIVER_PROPERTY));
        properties.put(JOPAPersistenceProperties.JPA_PERSISTENCE_PROVIDER, JOPAPersistenceProvider.class.getName());
        properties.put(Rdf4jOntoDriverProperties.USE_VOLATILE_STORAGE, Boolean.TRUE.toString());
        properties.put(Rdf4jOntoDriverProperties.USE_INFERENCE, Boolean.TRUE.toString());
        properties.put(Rdf4jOntoDriverProperties.INFERENCE_IN_DEFAULT_CONTEXT, Boolean.TRUE.toString());

        properties.put(JOPAPersistenceProperties.CACHE_ENABLED, Boolean.FALSE.toString());
        this.emf = Persistence.createEntityManagerFactory("multiple-inheritance-demo", properties);
    }

    @PreDestroy
    private void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

    private static Map<String, String> initParams() {
        final Map<String, String> map = new HashMap<>();
        map.put(JOPAPersistenceProperties.SCAN_PACKAGE, "cz.cvut.kbss.jopa.multipleinheritancedemo.model");
        map.put(JOPAPersistenceProperties.JPA_PERSISTENCE_PROVIDER, JOPAPersistenceProvider.class.getName());
        return map;
    }
}
