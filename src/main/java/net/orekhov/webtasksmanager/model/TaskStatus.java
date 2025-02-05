package net.orekhov.webtasksmanager.model;

import lombok.Getter;

/**
 * Перечисление для представления различных статусов задачи.
 * Каждый статус имеет описание, которое поможет понять текущее состояние задачи.
 */
@Getter  // Генерирует геттеры для всех полей
public enum TaskStatus {

    RESEARCHING("На анализе."),
    TO_PROCESSING("В работе."),
    CANCELLED("Отменена."),
    COMPLETED("Завершена.");

    private String value;  // Описание статуса задачи

    /**
     * Конструктор перечисления для присвоения значения каждому статусу.
     *
     * @param value Описание статуса задачи.
     */
    TaskStatus(String value) {
        this.value = value;
    }
}
