package com.krjaken.wtf.core.memory.db.dtos;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Entity
@Table(name = "lenguages")
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

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "lenguage_eng", nullable = false)
    public String getLenguage_eng() {
        return lenguage_eng;
    }

    @Column(name = "lenguage_original", nullable = false)
    public String getLenguage_original() {
        return lenguage_original;
    }

    @Override
    public String toString() {
        return "Language [id=" + id + ", lenguage_eng=" + lenguage_eng + ", lenguage_original=" + lenguage_original + "]";
    }
}
