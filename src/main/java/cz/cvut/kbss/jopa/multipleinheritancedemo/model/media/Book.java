package cz.cvut.kbss.jopa.multipleinheritancedemo.model.media;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;
import cz.cvut.kbss.jopa.vocabulary.DC;

import java.net.URI;


@NamedNativeQueries({@NamedNativeQuery(name = "Book.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Book + "> . }"), @NamedNativeQuery(name = "Book.findByISNB", query = "SELECT ?x WHERE {?x <" + Vocabulary.book_p_ISBN + "> ?ISNB . }")})
@OWLClass(iri = Vocabulary.Book)
public interface Book {


    @OWLObjectProperty(iri = Vocabulary.book_p_author, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Person getAuthor();

    public void setAuthor(Person author);

    public String getTitle();

    @OWLDataProperty(iri = DC.Terms.TITLE)
    public void setTitle(String title);


    @OWLDataProperty(iri = Vocabulary.book_p_ISBN)
    public String getISNB();

    public void setISNB(String ISNB);

    URI getUri();

}
