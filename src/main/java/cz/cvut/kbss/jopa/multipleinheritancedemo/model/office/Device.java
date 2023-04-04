package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.NamedNativeQueries;
import cz.cvut.kbss.jopa.model.annotations.NamedNativeQuery;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.util.Set;
@NamedNativeQueries({
        @NamedNativeQuery(name = "Device.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Device + "> . }"),
})
@OWLClass(iri = Vocabulary.Device)
public interface Device {

    @OWLDataProperty(iri = Vocabulary.device_p_connectors)
    void setConnectors(Set<String> connectors);

    @OWLDataProperty(iri = Vocabulary.device_p_wireless)
    void setWireless(Set<String> wireless);
}
