package net.orekhov.webtasksmanager.model.jwt_authentication;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Класс, представляющий пользователя приложения (AppUser).
 * Хранит данные о пользователе, такие как имя, пароль и роли.
 * Этот класс используется для сохранения информации о пользователе в базе данных.
 */
@Entity // Указывает, что этот класс является сущностью JPA и будет сохранен в базе данных
@Data // Аннотация Lombok для автоматической генерации геттеров, сеттеров и других методов
@Builder // Аннотация Lombok для создания экземпляров класса с использованием паттерна "builder"
@AllArgsConstructor // Генерация конструктора с параметрами для всех полей
@NoArgsConstructor // Генерация конструктора без параметров
@FieldDefaults(level = AccessLevel.PRIVATE) // Устанавливает уровень доступа для полей (по умолчанию PRIVATE)
public class AppUser {

    @Id // Указывает, что это поле является идентификатором сущности
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Указывает, что идентификатор будет генерироваться автоматически
    Long id; // Уникальный идентификатор пользователя

    String username; // Имя пользователя (логин)

    String password; // Пароль пользователя

    @ElementCollection(fetch = FetchType.EAGER) // Указывает, что роли пользователя будут загружаться сразу при запросе пользователя
    @Enumerated(EnumType.STRING) // Указывает, что роли будут храниться как строки (например, "ROLE_USER")
    List<Role> roles; // Список ролей пользователя, которые определяют его права доступа

}
