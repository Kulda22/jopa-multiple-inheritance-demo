package cz.cvut.kbss.jopa.multipleinheritancedemo.model.office;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Vocabulary;

@OWLClass(iri = Vocabulary.Printer)
public interface Printer extends Device {
    @OWLDataProperty(iri = Vocabulary.printer_p_color)
    Boolean getColor();

    @OWLDataProperty(iri = Vocabulary.printer_p_DPI)
    Integer getDPI();

    @OWLDataProperty(iri = Vocabulary.printer_p_bwPagesPerMinute)
    Integer getBwPagesPerMinute();

    @OWLDataProperty(iri = Vocabulary.printer_p_colorPagesPerMinute)
    Integer getColorPagesPerMinute();
}
