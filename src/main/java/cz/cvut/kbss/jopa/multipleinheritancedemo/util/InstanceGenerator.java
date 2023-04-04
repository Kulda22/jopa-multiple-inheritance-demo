package cz.cvut.kbss.jopa.multipleinheritancedemo.util;

import com.github.javafaker.Faker;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.AudioBook;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Book;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.media.Person;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Copier;

import java.util.HashSet;
import java.util.Set;

public class InstanceGenerator {
    private Faker f = new Faker();

    public Book generateBook() {
        Book b = new Book();
        b.setISNB(f.code().isbn10());
        b.setTitle(f.book().title());
        b.setAuthor(generatePerson());
        return b;
    }

    public AudioBook generateAudioBook() {
        AudioBook ab = new AudioBook();

        ab.setInterpret(generatePerson());
        ab.setTrackCount(f.number().numberBetween(1, 100));

        ab.setISNB(f.code().isbn10());
        ab.setTitle(f.book().title());
        ab.setAuthor(generatePerson());

        return ab;
    }

    public Copier generateCopier() {
        Copier c = new Copier();

        c.setColor(f.bool().bool());
        c.setFreestanding(f.bool().bool());
        c.setDPI(f.number().numberBetween(10, 100000));
        c.setScanDPI(f.number().numberBetween(10, 100000));
        c.setBwPagesPerMinute(f.number().randomDigit());
        c.setColorPagesPerMinute(f.number().randomDigit());
        c.setPagesPerMinute(f.number().randomDigit());

        Set<String> connectors = new HashSet<>();
        Set<String> wireless = new HashSet<>();
        for (int i = 0; i < f.number().randomDigit(); i++) {
            connectors.add(f.pokemon().name());
            wireless.add(f.space().nebula());
        }

        c.setConnectors(connectors);
        c.setWireless(wireless);
        c.generateUri();
        return c;
    }


    public Person generatePerson() {
        Person p = new Person();
        p.setFirstName(f.name().firstName());
        p.setLastName(f.name().lastName());
        p.generateUri();
        return p;
    }
}
