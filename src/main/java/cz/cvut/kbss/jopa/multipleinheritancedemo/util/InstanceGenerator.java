package cz.cvut.kbss.jopa.multipleinheritancedemo.util;

import com.github.javafaker.Faker;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Book;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Person;

public class InstanceGenerator {
    private Faker f = new Faker();

    public Book createBook() {
        Book b = new Book();
        b.setISNB(f.code().isbn10());
        b.setTitle(f.book().title());
        b.setAuthor(generatePerson());
        return b;
    }

    public Person generatePerson() {
        Person p = new Person();
        p.setFirstName(f.name().firstName());
        p.setLastName(f.name().lastName());
        p.generateUri();
        return p;
    }
}
