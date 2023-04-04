package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.util.Set;

@OWLClass(iri = Vocabulary.Device)
public interface Device {

    @OWLDataProperty(iri = Vocabulary.device_p_connectors)
    void setConnectors(Set<String> connectors);

    @OWLDataProperty(iri = Vocabulary.device_p_wireless)
    void setWireless(Set<String> wireless);
}
