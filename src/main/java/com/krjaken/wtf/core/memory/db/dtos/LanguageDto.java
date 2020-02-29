package com.krjaken.wtf.core.memory.db.dtos;

import lombok.Data;

@Data
public class LanguageDto {
    private Long id;
    private String lenguage_eng;
    private String lenguage_original;

    public LanguageDto() {
    }

    public LanguageDto(Long id, String lenguage_eng, String lenguage_original) {
        this.id = id;
        this.lenguage_eng = lenguage_eng;
        this.lenguage_original = lenguage_original;
    }

    @Override
    public String toString() {
        return "Language [id=" + id + ", lenguage_eng=" + lenguage_eng + ", lenguage_original=" + lenguage_original + "]";
    }
}
