package cz.cvut.kbss.jopa.multipleinheritancedemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.kbss.jopa.multipleinheritancedemo.config.SerializationConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBase {

    protected ObjectMapper objectMapper;
    public TestBase() {
        this.objectMapper = SerializationConfig.createObjectMapper();
    }


    protected String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }


    protected <T> T readValue(MvcResult result, Class<T> targetType) throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsByteArray(), targetType);
    }

    protected <T> T readValue(MvcResult result, TypeReference<T> targetType) throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsByteArray(), targetType);
    }

    void verifyLocationEquals(String expectedPath, MvcResult result) {
        final String locationHeader = result.getResponse().getHeader(HttpHeaders.LOCATION);
        assertNotNull(locationHeader);
        final String path = locationHeader.substring(0,
                locationHeader.indexOf('?') != -1 ? locationHeader.indexOf('?') : locationHeader.length());
        assertEquals("http://localhost" + expectedPath, path);
    }
}