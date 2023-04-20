package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.NamedNativeQueries;
import cz.cvut.kbss.jopa.model.annotations.NamedNativeQuery;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;
@NamedNativeQueries({
        @NamedNativeQuery(name = "Scanner.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Scanner + "> . }"),
})
@OWLClass(iri = Vocabulary.Scanner)

public interface Scanner extends Device {
    @OWLDataProperty(iri = Vocabulary.scanner_p_scanDPI)
    void setScanDPI(Integer scanDpi);

    @OWLDataProperty(iri = Vocabulary.scanner_p_scansPerMinute)
    void setScansPerMinute(Integer pagesPerMinute);

}
