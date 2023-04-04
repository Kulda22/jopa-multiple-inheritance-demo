package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

@OWLClass(iri = Vocabulary.Scanner)

public interface Scanner extends Device {
    @OWLDataProperty(iri = Vocabulary.scanner_p_scanDPI)
    void setScanDPI(Integer scanDpi);

    @OWLDataProperty(iri = Vocabulary.scanner_p_pagesPerMinute)
    void setPagesPerMinute(Integer pagesPerMinute);

}
