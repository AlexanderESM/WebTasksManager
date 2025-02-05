package net.orekhov.webtasksmanager.controllers.jwt;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import net.orekhov.webtasksmanager.model.jwt_authentication.util.JwtUtil;
import net.orekhov.webtasksmanager.security.jwt_authentication.UserPrincipal;
import net.orekhov.webtasksmanager.services.UserPrincipalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для обработки запросов, связанных с аутентификацией.
 * Этот контроллер предоставляет эндпоинт для аутентификации пользователей
 * и генерации JWT-токенов.
 */
@RestController
@RequestMapping("/auth")
public class AuthControllerWithCookies {

    @Resource // Внедрение зависимости сервиса для работы с пользовательскими данными
    private UserPrincipalService userDetailsService;

    @Resource // Внедрение зависимости утилиты для работы с JWT-токенами
    private JwtUtil jwtUtil;

    @Resource // Внедрение зависимости AuthenticationManager для аутентификации пользователей
    private AuthenticationManager authenticationManager;

    /**
     * Эндпоинт для аутентификации пользователя и генерации JWT-токена.
     * После успешной аутентификации токен сохраняется в cookies.
     *
     * @param username имя пользователя, передаваемое в запросе
     * @param password пароль пользователя, передаваемый в запросе
     * @param response HTTP-ответ для установки cookies
     * @return ResponseEntity с сообщением об успешной аутентификации
     */
    @PostMapping("/authenticate")
    public ResponseEntity<String> createAuthenticationToken(@RequestParam String username, // Получаем username из параметров запроса
                                                            @RequestParam String password, // Получаем password из параметров запроса
                                                            HttpServletResponse response) {
        try {
            // Аутентификация пользователя с использованием AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Загрузка данных пользователя, необходимого для генерации JWT
            UserPrincipal userDetails = userDetailsService.loadUserByUsername(username);

            // Генерация JWT-токена для аутентифицированного пользователя
            String token = jwtUtil.generateToken(userDetails);

            // Создание и настройка cookie для хранения JWT-токена
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setHttpOnly(true); // Защита от XSS (невозможность доступа через JavaScript)
            cookie.setSecure(true); // Только для HTTPS соединений
            cookie.setPath("/"); // Доступность cookie для всех путей приложения
            response.addCookie(cookie);

            // Перенаправление на страницу задач после успешной аутентификации
            response.sendRedirect("/tasks");

            // Возвращаем успешный ответ с сообщением
            return ResponseEntity.ok("Authentication successful! Token has been set in cookies.");
        } catch (Exception e) {
            // В случае ошибки аутентификации возвращаем ошибку с сообщением
            return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
        }
    }
}
