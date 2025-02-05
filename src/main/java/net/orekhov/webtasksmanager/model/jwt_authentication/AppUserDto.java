package net.orekhov.webtasksmanager.model.jwt_authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) для пользователя приложения (AppUser).
 * Используется для передачи данных пользователя (имя, пароль и роли) между слоями приложения.
 */
@Data // Аннотация Lombok для генерации геттеров, сеттеров и других методов
@Builder // Паттерн "builder" для удобного создания экземпляров класса
@AllArgsConstructor // Генерация конструктора с параметрами для всех полей
@NoArgsConstructor // Генерация конструктора без параметров
public class AppUserDto {

    String username; // Имя пользователя (логин)

    String password; // Пароль пользователя

    List<Role> roles; // Список ролей пользователя, определяющих его права доступа

}
