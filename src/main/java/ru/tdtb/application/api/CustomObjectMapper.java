package ru.tdtb.application.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.registerModule(new JavaTimeModule());
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
