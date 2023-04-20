package cz.cvut.kbss.jopa.multipleinheritancedemo.model.media;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.NamedNativeQueries;
import cz.cvut.kbss.jopa.model.annotations.NamedNativeQuery;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.net.URI;
import java.util.Objects;


@NamedNativeQueries({@NamedNativeQuery(name = "AudioBook.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.AudioBook + "> . }"), @NamedNativeQuery(name = "AudioBook.findByISBN", query = "SELECT ?x WHERE {?x <" + Vocabulary.book_p_ISBN + "> ?ISBN . }")})
@OWLClass(iri = Vocabulary.AudioBook)
public class AudioBook implements Recording, Book {
    @Id
    protected URI uri;
    protected Person interpret;
    protected Integer trackCount;
    protected Person author;

    protected String title;

    protected String ISBN;

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public Person getInterpret() {
        return interpret;
    }

    public void setInterpret(Person interpret) {
        this.interpret = interpret;
    }

    @Override
    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    @Override
    public Person getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getISBN() {
        return ISBN;
    }

    @Override
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert interpret != null;
        assert interpret.getLastName() != null;
        assert ISBN != null;

        this.uri = URI.create(Vocabulary.URI_BASE + ISBN);
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "uri=" + uri +
                ", interpret=" + interpret +
                ", trackCount=" + trackCount +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioBook audioBook = (AudioBook) o;

        if (!Objects.equals(uri, audioBook.uri)) return false;
        if (!Objects.equals(interpret, audioBook.interpret)) return false;
        if (!Objects.equals(trackCount, audioBook.trackCount)) return false;
        if (!Objects.equals(author, audioBook.author)) return false;
        if (!Objects.equals(title, audioBook.title)) return false;
        return Objects.equals(ISBN, audioBook.ISBN);
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (interpret != null ? interpret.hashCode() : 0);
        result = 31 * result + (trackCount != null ? trackCount.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }
}
