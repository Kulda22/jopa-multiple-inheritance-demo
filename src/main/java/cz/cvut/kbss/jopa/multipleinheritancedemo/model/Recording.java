package cz.cvut.kbss.jopa.multipleinheritancedemo.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.net.URI;

@NamedNativeQueries({
        @NamedNativeQuery(name = "Recording.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Recording + "> . }"),
})
@OWLClass(iri = Vocabulary.Recording)
public interface Recording {

    URI getUri();

    @OWLObjectProperty(iri = Vocabulary.recording_p_interpret, cascade = CascadeType.ALL)
    Person getInterpret();

    @OWLDataProperty(iri = Vocabulary.recording_p_trackCount)
    Integer getTrackCount();

    @OWLDataProperty(iri = Vocabulary.recording_p_title)
    void setTitle(String title);
    void generateUri();

}