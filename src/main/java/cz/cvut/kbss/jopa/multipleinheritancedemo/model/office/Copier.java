package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.net.URI;
import java.util.Set;



@NamedNativeQueries({
        @NamedNativeQuery(name = "Copier.findAll", query = "SELECT ?x WHERE { ?x a <" + Vocabulary.Copier + "> . }"),
        @NamedNativeQuery(name = "Copier.findByKey", query = "SELECT ?x WHERE {?x <" + Vocabulary.p_key + "> ?key . }")
})
@OWLClass(iri = Vocabulary.Copier)
public class Copier implements Printer, Scanner {

    @Id
    private URI uri;

    @OWLDataProperty(iri = Vocabulary.p_key)
    private String key;
    @OWLDataProperty(iri = Vocabulary.copier_p_freeStanding)
    protected Boolean freestanding;

    protected Set<String> connectors;
    protected Set<String> wireless;
    protected Boolean color;

    protected Integer printDPI;
    protected Integer scanDPI;
    protected Integer bwPagesPerMinute;
    protected Integer colorPagesPerMinute;
    protected Integer scansPerMinute;

    public Boolean getFreestanding() {
        return freestanding;
    }

    public void setFreestanding(Boolean freestanding) {
        this.freestanding = freestanding;
    }

    public Set<String> getConnectors() {
        return connectors;
    }

    @Override
    public void setConnectors(Set<String> connectors) {
        this.connectors = connectors;
    }

    public Set<String> getWireless() {
        return wireless;
    }

    @Override
    public void setWireless(Set<String> wireless) {
        this.wireless = wireless;
    }

    @Override
    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    @Override
    public Integer getPrintDPI() {
        return printDPI;
    }

    public void setPrintDPI(Integer printDPI) {
        this.printDPI = printDPI;
    }

    public Integer getScanDPI() {
        return scanDPI;
    }

    @Override
    public void setScanDPI(Integer scanDPI) {
        this.scanDPI = scanDPI;
    }

    @Override
    public Integer getBwPagesPerMinute() {
        return bwPagesPerMinute;
    }

    public void setBwPagesPerMinute(Integer bwPagesPerMinute) {
        this.bwPagesPerMinute = bwPagesPerMinute;
    }

    @Override
    public Integer getColorPagesPerMinute() {
        return colorPagesPerMinute;
    }

    public void setColorPagesPerMinute(Integer colorPagesPerMinute) {
        this.colorPagesPerMinute = colorPagesPerMinute;
    }

    public Integer getScansPerMinute() {
        return scansPerMinute;
    }

    @Override
    public void setScansPerMinute(Integer scansPerMinute) {
        this.scansPerMinute = scansPerMinute;
    }

    public URI getUri() {
        return uri;
    }

    public String getKey() {
        return key;
    }

    public void generateKey() {
        if (key != null) {
            return;
        }
        this.key = Long.toString(System.currentTimeMillis());
    }

    public void generateUri() {
        generateKey();

        if (uri != null) {
            return;
        }
        this.uri = URI.create(Vocabulary.URI_BASE + "copier-" + key);
    }
}
