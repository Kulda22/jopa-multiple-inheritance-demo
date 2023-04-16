package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.util.Set;
@NamedNativeQueries({
        @NamedNativeQuery(name = "Device.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Device + "> . }"),
})
@OWLClass(iri = Vocabulary.Device)
public interface Device {

    @OWLDataProperty(iri = Vocabulary.device_p_connectors,simpleLiteral = true)
    void setConnectors(Set<String> connectors);

    @OWLDataProperty(iri = Vocabulary.device_p_wireless,simpleLiteral = true)
    void setWireless(Set<String> wireless);
}
