package com.krjaken.wtf.core.languages.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class ConceptDto {
    private Integer id;
    private String conceptExample;
    private String prototype;
    private Map<String, List<ConceptDto>> parameters;

    public String createCypherScript() {
        return "Concept{conceptExample:'" + conceptExample + "'" + (prototype != null ? ", prototype:'" + prototype + "'}" : "}");
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
