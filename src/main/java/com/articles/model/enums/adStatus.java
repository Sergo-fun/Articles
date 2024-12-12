package com.articles.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum adStatus {
    WAITING("Ожидание"),
    PAY("Оплата"),
    ACTIVE("Активно"),
    SOLD("Закрыто"),
    REFUSED("Отказано"),
    ;

    private final String name;
}

