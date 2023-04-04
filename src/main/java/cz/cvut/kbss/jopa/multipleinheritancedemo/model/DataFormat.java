package cz.cvut.kbss.jopa.multipleinheritancedemo.model;

public enum DataFormat {

    JSON, RDFXML, TURTLE;

    public static DataFormat fromString(String str) {
        for (DataFormat df : values()) {
            if (str.equalsIgnoreCase(df.toString())) {
                return df;
            }
        }
        throw new IllegalArgumentException("No matching data format found for string " + str);
    }
}
