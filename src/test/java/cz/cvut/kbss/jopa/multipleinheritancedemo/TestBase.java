package cz.cvut.kbss.jopa.multipleinheritancedemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.kbss.jopa.multipleinheritancedemo.config.SerializationConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.accept.ContentNegotiationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBase {


    protected ObjectMapper objectMapper;

//    protected MockMvc mockMvc;


    public TestBase() {

    }

    public void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
////                .setMessageConverters(createJsonLdMessageConverter(),
////                        createDefaultMessageConverter(), createStringEncodingMessageConverter(),
////                        createResourceMessageConverter())
////                .setUseSuffixPatternMatch(false)
//                .setContentNegotiationManager(new ContentNegotiationManager())
//                .build();

        this.objectMapper = SerializationConfig.getObjectMapper();


    }

//    protected void setupObjectMappers() {
//        this.objectMapper = Environment.getObjectMapper();
//        this.jsonLdObjectMapper = Environment.getJsonLdObjectMapper();
//    }

    protected String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

//    protected String toJsonLd(Object object) throws Exception {
//        return jsonLdObjectMapper.writeValueAsString(object);
//    }

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