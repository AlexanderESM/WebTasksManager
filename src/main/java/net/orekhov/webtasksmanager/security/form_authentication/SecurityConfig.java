package net.orekhov.webtasksmanager.security.form_authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
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
 */
//@Configuration // Указывает, что класс является конфигурационным и содержит бины Spring
//@EnableWebSecurity // Включает поддержку безопасности для веб-приложения
public class SecurityConfig {

    /**
     * Настроить цепочку фильтров безопасности для HTTP-запросов.
     * Включает настройку доступа к страницам и формы входа/выхода.
     *
     * @param http Объект для настройки безопасности HTTP-запросов.
     * @return SecurityFilterChain, который определяет правила безопасности.
     * @throws Exception если произошла ошибка при настройке.
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Настройка авторизации запросов
//                .authorizeHttpRequests(auth -> auth
//                        //.requestMatchers("/").permitAll() // Разрешить доступ к "/tasks" без аутентификации
//                        .anyRequest().authenticated() // Все запросы требуют аутентификации
//                )
//                // Настройка формы входа
//                .formLogin(form -> form
//                        .loginPage("/login") // Указать страницу входа
//                        .permitAll() // Разрешить доступ к странице входа всем пользователям
//                )
//                // Настройка выхода из системы
//                .logout(LogoutConfigurer::permitAll); // Разрешить выход из системы всем пользователям
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
//                .password(passwordEncoder().encode("password")) // Кодирование пароля с использованием PasswordEncoder
//                .roles("USER") // Роль пользователя
//                .build();
//
//        // Возвращаем менеджер пользователей, который хранит пользователя в памяти
//        return new InMemoryUserDetailsManager(user); // Для более реального приложения рассмотрите базу данных для хранения пользователей
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
