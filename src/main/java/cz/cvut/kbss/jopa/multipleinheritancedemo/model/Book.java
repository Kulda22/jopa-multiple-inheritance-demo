package cz.cvut.kbss.jopa.multipleinheritancedemo.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.io.Serializable;
import java.net.URI;


@NamedNativeQueries({
        @NamedNativeQuery(name = "Book.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Book + "> . }"),
        @NamedNativeQuery(name = "Book.findByISNB", query = "SELECT ?x WHERE {?x <" + Vocabulary.book_p_ISBN + "> ?ISNB . }")
})
@OWLClass(iri = Vocabulary.Book)
public class Book implements Serializable {
    @Id
    protected URI uri;

    @OWLObjectProperty(iri = Vocabulary.book_p_author, cascade = CascadeType.ALL)
    protected Person author;
    @OWLDataProperty(iri = Vocabulary.book_p_title)

    protected String title;
    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.book_p_ISBN)
    protected String ISNB;

    public Book(Person author, String title, String ISNB) {
        this.author = author;
        this.title = title;
        this.ISNB = ISNB;
    }

    public Book() {
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISNB() {
        return ISNB;
    }

    public void setISNB(String ISNB) {
        this.ISNB = ISNB;
    }


    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert author != null;
        assert title != null;
        this.uri = URI.create(Vocabulary.URI_BASE + ISNB);
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uri=" + uri +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", isbn='" + ISNB + '\'' +
                '}';
    }
}
