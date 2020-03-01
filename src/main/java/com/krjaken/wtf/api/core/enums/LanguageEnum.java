package com.krjaken.wtf.api.core.enums;

import lombok.Getter;

@Getter
public enum LanguageEnum {
    ENGLISH("english"),
    RUSSIAN("russian");

    private String engName;

    LanguageEnum(String engName) {
        this.engName = engName;
    }
}
