package cz.cvut.kbss.jopa.multipleinheritancedemo.model;


public class Vocabulary {

    public static final String URI_BASE = "http://onto.fel.cvut.cz/ontologies/example04/";
    public static final String p_key = URI_BASE + "key";

    public static final String Student = URI_BASE + "Student";
    public static final String student_p_firstName = URI_BASE + "firstName";
    public static final String student_p_lastName = URI_BASE + "lastName";
    public static final String student_p_emailAddress = URI_BASE + "emailAddress";
    public static final String student_p_key = URI_BASE + "key";

    public static final String Book = URI_BASE + "Book";
    public static final String book_p_author = URI_BASE + "author";
    public static final String book_p_title = URI_BASE + "title";
    public static final String book_p_ISBN = URI_BASE + "ISBN";

    public static final String Person = URI_BASE + "Person";
    public static final String person_p_firstName = URI_BASE + "firstName";
    public static final String person_p_lastName = URI_BASE + "lastName";

}
