package com.articles.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    BUSINESS("Бизнес"),
    JOB("Работа"),
    SERVICES("Услуги"),
    OTHER("Другое"),
    ;

    private final String name;
}

