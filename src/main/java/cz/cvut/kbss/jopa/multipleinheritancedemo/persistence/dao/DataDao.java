package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/// works only with rdf4j for now
@Component
public class DataDao {

    private static final Logger LOG = LoggerFactory.getLogger(DataDao.class);

//    @Autowired
//    private Repository repository;

    public String getRepositoryData(DataFormat format) {
        return ":(";
//        try {
//            final RepositoryConnection connection = repository.getConnection();
//            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            final RDFHandler rdfHandler = getHandler(format, bos);
//            connection.export(rdfHandler);
//            connection.close();
//            return bos.toString();
//        } catch (RepositoryException | RDFHandlerException e) {
//            LOG.error("Unable to read data from repository.", e);
//            return "";
//        }
    }

//    private RDFHandler getHandler(DataFormat format, OutputStream os) {
//        switch (format) {
//            case JSON:
//                return new RDFJSONWriter(os, RDFFormat.RDFJSON);
//            case RDFXML:
//                return new RDFXMLPrettyWriter(os);
//            case TURTLE:
//                return new TurtleWriter(os);
//            default:
//                throw new IllegalArgumentException("Unsupported data format: " + format);
//        }
//    }
}