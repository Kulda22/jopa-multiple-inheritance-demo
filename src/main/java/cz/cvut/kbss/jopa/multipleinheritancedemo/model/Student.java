package cz.cvut.kbss.jopa.multipleinheritancedemo.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.io.Serializable;
import java.net.URI;

@NamedNativeQueries({
        @NamedNativeQuery(name = "Student.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Student + "> . }"),
        @NamedNativeQuery(name = "Student.findByKey", query = "SELECT ?x WHERE {?x <" + Vocabulary.student_p_key + "> ?key . }")
})
@OWLClass(iri = Vocabulary.Student)
public class Student implements Serializable {

    @Id
    private URI uri;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.student_p_key, simpleLiteral = true)
    private String key;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.student_p_firstName)
    private String firstName;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = Vocabulary.student_p_lastName)
    private String lastName;

    @OWLDataProperty(iri = Vocabulary.student_p_emailAddress)
    private String email;

    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert firstName != null;
        assert lastName != null;
        this.uri = URI.create(Vocabulary.URI_BASE + firstName + '+' + lastName);
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uri=" + uri +
                ", key='" + key + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

