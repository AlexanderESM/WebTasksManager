package net.orekhov.webtasksmanager.controllers.jwt;

import jakarta.annotation.Resource;
import net.orekhov.webtasksmanager.model.jwt_authentication.util.AuthDto;
import net.orekhov.webtasksmanager.model.jwt_authentication.util.JwtUtil;
import net.orekhov.webtasksmanager.services.UserPrincipalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для аутентификации пользователей с использованием JWT.
 * Этот контроллер управляет процессом аутентификации пользователя и генерацией JWT токена.
 */
@RestController
@RequestMapping
public class AuthControllerWithAuthentication {

    @Resource
    private UserPrincipalService userDetailsService;  // Сервис для работы с данными пользователя

    @Resource
    private JwtUtil jwtUtil;  // Утилита для создания и проверки JWT токенов

    @Resource
    private AuthenticationManager authenticationManager;  // Менеджер аутентификации для выполнения процесса аутентификации

    /**
     * Метод для аутентификации пользователя и генерации JWT токена.
     *
     * @param authDto объект, содержащий данные для аутентификации (имя пользователя и пароль)
     * @return ResponseEntity с JWT токеном в случае успешной аутентификации
     */
    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthDto authDto) {
        /*
         * Если приложение использует только один способ аутентификации,
         * можно обойтись без AuthenticationManager и реализовать аутентификацию вручную,
         * так как AuthenticationManager может показаться избыточным.
         */

        // Аутентификация с помощью переданных данных (имя пользователя и пароль)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
        );

        // Генерация JWT токена для аутентифицированного пользователя
        return ResponseEntity.ok(jwtUtil.generateToken(userDetailsService.loadUserByUsername(authDto.getUsername())));
    }
}
