package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

import java.net.URI;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Copier copier = (Copier) o;

        if (!Objects.equals(uri, copier.uri)) return false;
        if (!Objects.equals(key, copier.key)) return false;
        if (!Objects.equals(freestanding, copier.freestanding))
            return false;
        if (!Objects.equals(connectors, copier.connectors)) return false;
        if (!Objects.equals(wireless, copier.wireless)) return false;
        if (!Objects.equals(color, copier.color)) return false;
        if (!Objects.equals(printDPI, copier.printDPI)) return false;
        if (!Objects.equals(scanDPI, copier.scanDPI)) return false;
        if (!Objects.equals(bwPagesPerMinute, copier.bwPagesPerMinute))
            return false;
        if (!Objects.equals(colorPagesPerMinute, copier.colorPagesPerMinute))
            return false;
        return Objects.equals(scansPerMinute, copier.scansPerMinute);
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (freestanding != null ? freestanding.hashCode() : 0);
        result = 31 * result + (connectors != null ? connectors.hashCode() : 0);
        result = 31 * result + (wireless != null ? wireless.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (printDPI != null ? printDPI.hashCode() : 0);
        result = 31 * result + (scanDPI != null ? scanDPI.hashCode() : 0);
        result = 31 * result + (bwPagesPerMinute != null ? bwPagesPerMinute.hashCode() : 0);
        result = 31 * result + (colorPagesPerMinute != null ? colorPagesPerMinute.hashCode() : 0);
        result = 31 * result + (scansPerMinute != null ? scansPerMinute.hashCode() : 0);
        return result;
    }
}
