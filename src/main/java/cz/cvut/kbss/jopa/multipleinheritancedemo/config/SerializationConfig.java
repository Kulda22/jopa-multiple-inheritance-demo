package cz.cvut.kbss.jopa.multipleinheritancedemo.config;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SerializationConfig {

    @JsonFilter("filter_persistenceContext")
    static class PropertyFilterMixIn {
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper = mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        mapper.addMixIn(
                Object.class, PropertyFilterMixIn.class);


        String[] toIgnore = {"persistenceContext"};
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter_persistenceContext", SimpleBeanPropertyFilter.serializeAllExcept(toIgnore));
        mapper.setConfig(mapper.getSerializationConfig().withFilters(filters));
        return mapper;
    }

}

