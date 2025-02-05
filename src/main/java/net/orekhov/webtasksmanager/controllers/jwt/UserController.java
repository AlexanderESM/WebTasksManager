package net.orekhov.webtasksmanager.controllers.jwt;

import jakarta.annotation.Resource;
import net.orekhov.webtasksmanager.model.jwt_authentication.util.AuthDto;
import net.orekhov.webtasksmanager.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления пользователями.
 * Этот контроллер предоставляет эндпоинт для регистрации новых пользователей.
 */
@RestController
@RequestMapping
public class UserController {

    @Resource // Внедрение зависимости сервиса для работы с пользователями
    private AppUserService appUserService;

    /**
     * Эндпоинт для регистрации нового пользователя.
     *
     * @param authDto объект, содержащий данные для регистрации пользователя (например, имя пользователя и пароль)
     * @return ResponseEntity с сообщением об успешной регистрации
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody AuthDto authDto) {
        // Логирование полученного объекта authDto
        System.out.println(authDto);

        // Регистрация нового пользователя с помощью сервиса
        appUserService.registerAppUser(authDto);

        // Возвращаем успешный ответ с сообщением
        return ResponseEntity.ok("User registered successfully!");
    }
}
