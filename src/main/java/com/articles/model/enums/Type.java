package com.articles.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    COMMERCIAL("Коммерческая"),
    NOT_COMMERCIAL("Некоммерческая"),
    ;
    private final String name;
}

