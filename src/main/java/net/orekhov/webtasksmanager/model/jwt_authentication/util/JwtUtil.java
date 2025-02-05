package net.orekhov.webtasksmanager.model.jwt_authentication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import net.orekhov.webtasksmanager.security.jwt_authentication.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Утилита для работы с JWT-токенами.
 * Этот класс предоставляет методы для генерации, проверки и извлечения данных из JWT-токенов.
 * Используется для обеспечения безопасности в приложении.
 */
@Component // Указывает, что этот класс является компонентом Spring и будет управляться Spring-контейнером
public class JwtUtil {

    // Секретный ключ для подписи JWT (внедряется из конфигурации)
    @Value("your-256-bit-secret-32-characters-long")
    private String secret;

    // Время жизни токена (10 дней, внедряется из конфигурации)
    @Value("864000000")
    private long expirationTime;

    /**
     * Получение ключа для подписи JWT-токенов.
     * Проверяется, что секретный ключ имеет длину не менее 256 бит (32 символа).
     *
     * @return секретный ключ для подписи
     * @throws IllegalArgumentException если секретный ключ недостаточно длинный
     */
    private SecretKey getSigningKey() {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 characters) long");
        }
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Генерация JWT-токена на основе данных пользователя.
     * Включает имя пользователя и его роли в токен.
     *
     * @param userPrincipal объект, содержащий данные пользователя
     * @return сгенерированный JWT-токен
     */
    public String generateToken(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();

        // Добавляем имя пользователя в claims
        claims.put("username", userPrincipal.getUsername());

        // Добавляем роли пользователя в claims
        claims.put("authorities", userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        // Генерация токена с claims и временем истечения
        return createToken(claims, userPrincipal.getUsername());
    }

    /**
     * Создание JWT-токена с указанными данными.
     * Токен подписывается секретным ключом и включает время выпуска и истечения.
     *
     * @param claims дополнительные данные, которые будут включены в токен (например, роли)
     * @param subject имя пользователя
     * @return сгенерированный JWT-токен
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // Добавляем дополнительные данные
                .setSubject(subject) // Добавляем имя пользователя в токен
                .setIssuedAt(new Date(System.currentTimeMillis())) // Устанавливаем время создания токена
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Устанавливаем время истечения
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Подписываем токен с использованием секретного ключа
                .compact(); // Генерируем строку токена
    }

    /**
     * Извлечение имени пользователя из JWT-токена.
     *
     * @param token JWT-токен
     * @return имя пользователя, содержащиеся в токене
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлечение времени истечения из JWT-токена.
     *
     * @param token JWT-токен
     * @return время истечения токена
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Извлечение конкретного поля из JWT-токена.
     * Используется для извлечения произвольных данных, таких как имя пользователя или роли.
     *
     * @param token JWT-токен
     * @param claimsResolver функция, которая определяет, какое поле нужно извлечь
     * @param <T> тип данных, который будет извлечен
     * @return извлеченные данные
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Извлекаем все данные из токена
        return claimsResolver.apply(claims); // Применяем функцию для извлечения нужного поля
    }

    /**
     * Извлечение всех данных (claims) из JWT-токена.
     * Токен парсится и проверяется на валидность с использованием подписи.
     *
     * @param token JWT-токен
     * @return все данные, содержащиеся в токене
     * @throws RuntimeException если произошла ошибка при извлечении данных
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Указываем секретный ключ для проверки подписи
                    .build()
                    .parseClaimsJws(token) // Парсим токен
                    .getBody(); // Извлекаем тело токена (claims)
        } catch (Exception e) {
            // Если произошла ошибка при извлечении данных, выбрасываем исключение
            throw new RuntimeException("Не удалось извлечь данные из токена: " + e.getMessage());
        }
    }

    /**
     * Проверка, истек ли срок действия токена.
     * Проверяет, что время истечения токена наступило.
     *
     * @param token JWT-токен
     * @return true, если токен истек, иначе false
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Сравниваем время истечения с текущим временем
    }

    /**
     * Проверка валидности токена.
     * Проверяется, что токен не истек и что имя пользователя в токене совпадает с данными пользователя.
     *
     * @param token JWT-токен
     * @param userDetails данные пользователя, с которым проверяется токен
     * @return true, если токен валиден, иначе false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token); // Извлекаем имя пользователя из токена
            // Проверяем, что имя пользователя совпадает и что токен не истек
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            // Если произошла ошибка, токен считается недействительным
            return false;
        }
    }
}
