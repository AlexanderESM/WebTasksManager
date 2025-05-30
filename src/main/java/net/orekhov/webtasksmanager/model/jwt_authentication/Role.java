package net.orekhov.webtasksmanager.model.jwt_authentication;

/**
 * Перечисление ролей для пользователей приложения.
 * Определяет доступные роли, которые могут быть присвоены пользователю для определения его прав доступа.
 */
public enum Role {
    // Роль пользователя с ограниченными правами доступа
    USER,

    // Роль администратора с расширенными правами доступа
    ADMIN
}
