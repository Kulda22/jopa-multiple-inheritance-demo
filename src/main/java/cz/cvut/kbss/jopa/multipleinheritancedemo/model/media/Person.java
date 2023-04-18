package cz.cvut.kbss.jopa.multipleinheritancedemo.model.media;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

@OWLClass(iri = Vocabulary.Person)

public class Person implements Serializable {

    @Id
    private URI uri;



    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.person_p_firstName)
    private String firstName;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.person_p_lastName)
    private String lastName;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert firstName != null;
        assert lastName != null;
        this.uri = URI.create(Vocabulary.URI_BASE + firstName + '+' + lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "uri=" + uri +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!Objects.equals(uri, person.uri)) return false;
        if (!Objects.equals(firstName, person.firstName)) return false;
        return Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
