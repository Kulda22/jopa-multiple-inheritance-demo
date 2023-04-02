package cz.cvut.kbss.jopa.multipleinheritancedemo.model;

import cz.cvut.kbss.jopa.model.annotations.NamedNativeQueries;
import cz.cvut.kbss.jopa.model.annotations.NamedNativeQuery;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;


@NamedNativeQueries({
        @NamedNativeQuery(name = "AudioBook.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.AudioBook + "> . }"),
        @NamedNativeQuery(name = "AudioBook.findByISNB", query = "SELECT ?x WHERE {?x <" + Vocabulary.book_p_ISBN + "> ?ISNB . }")
})
@OWLClass(iri = Vocabulary.AudioBook)
public class AudioBook extends Book implements Recording {
    protected Person interpret;
    protected Integer trackCount;


    public void setInterpret(Person interpret) {
        this.interpret = interpret;
    }

    @Override
    public Person getInterpret() {
        return interpret;
    }

    @Override
    public Integer getTrackCount() {
        return trackCount;
    }


    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }
}
