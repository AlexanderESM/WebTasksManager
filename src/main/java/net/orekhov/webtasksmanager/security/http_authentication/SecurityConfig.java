package net.orekhov.webtasksmanager.security.http_authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс для настройки безопасности приложения.
 * Включает настройку цепочки фильтров безопасности, аутентификации и авторизации.
 * Использует HTTP Basic Authentication для аутентификации пользователей.
 */
//@Configuration // Указывает, что класс является конфигурационным и содержит бины Spring
//@EnableWebSecurity // Включает поддержку безопасности для веб-приложения
public class SecurityConfig {

    /**
     * Настраивает цепочку фильтров безопасности.
     *
     * @param http Объект для настройки безопасности HTTP-запросов.
     * @return SecurityFilterChain, который определяет правила безопасности.
     * @throws Exception Если произошла ошибка при настройке.
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Настройка авторизации запросов
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public").permitAll() // Разрешить доступ к "/public" без аутентификации
//                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//                )
//                // Включение HTTP Basic Authentication
//                .httpBasic(httpBasic -> {}); // Использование HTTP Basic Authentication для аутентификации
//
//        return http.build(); // Сборка и возврат настроенной цепочки фильтров
//    }

    /**
     * Создает сервис для работы с пользователями.
     * В данном случае используется in-memory хранилище пользователей.
     *
     * @return UserDetailsService, который управляет пользователями.
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // Создание пользователя с именем "user", паролем "password" и ролью "USER"
//        UserDetails user = User.builder()
//                .username("user") // Имя пользователя
//                .password(passwordEncoder().encode("password")) // Кодирование пароля
//                .roles("USER") // Роль пользователя
//                .build();
//
//        // Возврат менеджера пользователей, который хранит пользователя в памяти
//        return new InMemoryUserDetailsManager(user);
//    }

    /**
     * Создает кодировщик паролей.
     * Используется для кодирования паролей пользователей.
     *
     * @return PasswordEncoder, который использует алгоритм BCrypt.
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Использование BCrypt для кодирования паролей
//    }
}
