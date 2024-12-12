package com.articles.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Region {
    RUSSIA("Россия"),
    USA("США"),
    EUROPE("Европа"),
    ASIA("Азия"),
    CIS("СНГ"),
    UKRAINA("Украина"),
    BELARUS("Беларусь");
    private final String name;
}

