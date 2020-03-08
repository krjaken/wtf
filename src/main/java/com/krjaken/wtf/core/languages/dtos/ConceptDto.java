package com.krjaken.wtf.core.languages.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class ConceptDto {
    private String conceptExample;
    private ConceptDto prototype;
    private List<ConceptDto> value;
    private List<ConceptDto> synonyms;
    private List<ConceptDto> antonyms;


    public String createCypherScript() {
        return "Concept{conceptExample:'" + conceptExample + "', prototype:'" + prototype.conceptExample + "'}";
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
