package cz.cvut.kbss.jopa.multipleinheritancedemo;

import cz.cvut.kbss.jopa.multipleinheritancedemo.rest.AudioBookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MultipleInheritanceDemoApplicationTests {

    @Autowired
    private AudioBookController audioBookController;

    @Test
    void contextLoads() {
        assertThat(audioBookController).isNotNull();
    }

}
