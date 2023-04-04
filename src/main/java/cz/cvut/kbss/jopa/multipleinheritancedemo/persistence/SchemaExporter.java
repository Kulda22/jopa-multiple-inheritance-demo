package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;
import cz.cvut.kbss.ontodriver.jena.config.JenaOntoDriverProperties;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.jena.rdf.model.ResourceFactory.createResource;
import static org.apache.jena.rdf.model.ResourceFactory.createStatement;

@Component
public class SchemaExporter {

    @Value("${repository.type}")
    private String repoType;

    @Value("${repositoryUrl}")
    private String repoUrl;

    public void exportSchema() {
        switch (repoType) {
            case JenaOntoDriverProperties.TDB:
                exportToTDB();
                break;
            case JenaOntoDriverProperties.FUSEKI:
                exportToFuseki();
                break;
            default:
                throw new IllegalArgumentException("Unsupported Jena repository type " + repoType);
        }
    }

    private void exportToTDB() {
        final Dataset ds = TDBFactory.createDataset(repoUrl);
        try {
            ds.begin(ReadWrite.WRITE);
            export(ds);
            ds.commit();
        } finally {
            ds.close();
        }
    }

    private void export(Dataset ds) {
            final Model jenaModel = ds.getDefaultModel();
            jenaModel.add(createStatement(createResource(Vocabulary.AudioBook), RDFS.subClassOf,
                    createResource(Vocabulary.Recording)));
            jenaModel.add(createStatement(createResource(Vocabulary.AudioBook), RDFS.subClassOf,
                    createResource(Vocabulary.Book)));
            jenaModel.add(createStatement(createResource(Vocabulary.Printer), RDFS.subClassOf,
                    createResource(Vocabulary.Device)));
            jenaModel.add(createStatement(createResource(Vocabulary.Scanner), RDFS.subClassOf,
                    createResource(Vocabulary.Device)));
        jenaModel.add(createStatement(createResource(Vocabulary.Copier), RDFS.subClassOf,
                createResource(Vocabulary.Scanner)));
        jenaModel.add(createStatement(createResource(Vocabulary.Copier), RDFS.subClassOf,
                createResource(Vocabulary.Printer)));
    }

    private void exportToFuseki() {
        try (final RDFConnection connection = RDFConnectionFuseki.create().destination(repoUrl).build()) {
            connection.begin(ReadWrite.WRITE);
            final Dataset ds = connection.fetchDataset();
            export(ds);
            connection.putDataset(ds);
            connection.commit();
        }
    }
}
