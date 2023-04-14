package cz.cvut.kbss.jopa.multipleinheritancedemo.model.media;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;
import cz.cvut.kbss.jopa.vocabulary.DC;

import java.net.URI;

@NamedNativeQueries({
        @NamedNativeQuery(name = "Recording.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Recording + "> . }")
})
@OWLClass(iri = Vocabulary.Recording)
public interface Recording {

    URI getUri();

    @OWLObjectProperty(iri = Vocabulary.recording_p_interpret, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Person getInterpret();

    @OWLDataProperty(iri = Vocabulary.recording_p_trackCount)
    Integer getTrackCount();

    @OWLDataProperty(iri = DC.Terms.TITLE) /// stejna property nevadi ve vic parentech
    void setTitle(String title);
    void generateUri();

}
