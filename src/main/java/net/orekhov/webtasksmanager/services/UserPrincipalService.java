package net.orekhov.webtasksmanager.services;

import jakarta.annotation.Resource;
import net.orekhov.webtasksmanager.model.jwt_authentication.AppUser;
import net.orekhov.webtasksmanager.repository.AppUserRepository;
import net.orekhov.webtasksmanager.security.jwt_authentication.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис для загрузки информации о пользователе при аутентификации.
 * Реализует интерфейс {@link UserDetailsService} для работы с Spring Security.
 */
@Service
public class UserPrincipalService implements UserDetailsService {

    @Resource // Внедрение зависимости для работы с репозиторием пользователей
    private AppUserRepository appUserRepository;

    /**
     * Загружает пользователя по имени пользователя (логину).
     * Используется Spring Security для аутентификации.
     *
     * @param username Имя пользователя.
     * @return Объект {@link UserPrincipal}, содержащий данные пользователя.
     * @throws UsernameNotFoundException Если пользователь с таким именем не найден.
     */
    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        // Поиск пользователя в базе данных по имени пользователя
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Создание объекта UserPrincipal, содержащего данные пользователя для Spring Security
        return UserPrincipal.builder()
                .username(user.getUsername()) // Установка имени пользователя
                .password(user.getPassword()) // Установка зашифрованного пароля
                .roles(user.getRoles()) // Установка ролей пользователя
                .build();
    }
}
