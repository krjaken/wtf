package com.krjaken.wtf.core.memory.db.dtos;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity("Language")
public class LanguageDto {
    @Property("eng")
    private String lenguage_eng;
    @Property("origin")
    private String lenguage_original;

    public LanguageDto() {
    }

    public LanguageDto(String lenguage_eng, String lenguage_original) {
        this.lenguage_eng = lenguage_eng;
        this.lenguage_original = lenguage_original;
    }

    @Override
    public String toString() {
        return "Language{eng:'" + lenguage_eng + "', origin:'" + lenguage_original + "'}";
    }
}
