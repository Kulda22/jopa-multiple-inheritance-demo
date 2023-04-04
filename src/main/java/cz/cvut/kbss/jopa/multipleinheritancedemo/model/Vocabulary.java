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
    public static final String book_p_title = URI_BASE + "book-title";
    public static final String book_p_ISBN = URI_BASE + "ISBN";

    public static final String Person = URI_BASE + "Person";
    public static final String person_p_firstName = URI_BASE + "firstName";
    public static final String person_p_lastName = URI_BASE + "lastName";

    public static final String Recording = URI_BASE + "Recording";
    public static final String recording_p_interpret = URI_BASE + "interpret";
    public static final String recording_p_trackCount = URI_BASE + "trackCount";
    public static final String recording_p_title = URI_BASE + "record-title";

    public static final String AudioBook = URI_BASE + "AudioBook";
    public static final String Device = URI_BASE + "Device";
    public static final String device_p_connectors = URI_BASE + "connectors";
    public static final String device_p_wireless = URI_BASE + "wireless";

    public static final String Printer = URI_BASE + "Printer";
    public static final String printer_p_color = URI_BASE + "color";
    public static final String printer_p_DPI = URI_BASE + "DPI";
    public static final String printer_p_bwPagesPerMinute = URI_BASE + "bwPagesPerMinute";
    public static final String printer_p_colorPagesPerMinute = URI_BASE + "colorPagesPerMinute";

    public static final String Scanner = URI_BASE + "Scanner";
    public static final String scanner_p_scanDPI = URI_BASE + "scanDPI";
    public static final String scanner_p_pagesPerMinute = URI_BASE + "pagesPerMinute";
    public static final String Copier = URI_BASE + "Copier";

    public static final String copier_p_freeStanding = URI_BASE + "freeStanding";
}
