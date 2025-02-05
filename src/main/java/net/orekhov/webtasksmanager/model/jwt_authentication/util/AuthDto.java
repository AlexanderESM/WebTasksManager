package net.orekhov.webtasksmanager.model.jwt_authentication.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект для передачи данных аутентификации (имя пользователя и пароль).
 * Используется для аутентификации пользователей и генерации JWT токенов.
 */
@Data // Генерирует геттеры, сеттеры, equals(), hashCode() и toString()
@Builder // Позволяет использовать паттерн "строитель" для создания объектов
@AllArgsConstructor // Генерирует конструктор с аргументами для всех полей
@NoArgsConstructor // Генерирует конструктор без аргументов
public class AuthDto {
    private String username;  // Имя пользователя
    private String password;  // Пароль
}
