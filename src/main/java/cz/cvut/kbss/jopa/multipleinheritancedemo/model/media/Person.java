package cz.cvut.kbss.jopa.multipleinheritancedemo.model.media;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.io.Serializable;
import java.net.URI;

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
}
