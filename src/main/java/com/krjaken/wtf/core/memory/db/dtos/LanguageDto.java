package com.krjaken.wtf.core.memory.db.dtos;

import lombok.Data;

@Data
public class LanguageDto {
    private String lenguage_eng;
    private String lenguage_original;

    public LanguageDto() {
    }

    public LanguageDto(String lenguage_eng, String lenguage_original) {
        this.lenguage_eng = lenguage_eng;
        this.lenguage_original = lenguage_original;
    }

    @Override
    public String toString() {
        return "Language [lenguage_eng=" + lenguage_eng + ", lenguage_original=" + lenguage_original + "]";
    }
}
