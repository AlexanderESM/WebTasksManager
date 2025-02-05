package net.orekhov.webtasksmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для обработки запросов, связанных с домашней страницей и регистрацией.
 * Этот контроллер управляет рендерингом форм для входа и регистрации пользователей.
 */
@Controller
public class HomeController {

    /**
     * Эндпоинт для отображения страницы входа пользователя.
     *
     * @return строка с именем шаблона для отображения страницы входа
     */
    @GetMapping("/login")
    public String login() {
        // Возвращает шаблон login.html для отображения формы входа
        return "tasks/login";
    }

    /**
     * Эндпоинт для отображения страницы регистрации пользователя.
     *
     * @return строка с именем шаблона для отображения формы регистрации
     */
    @GetMapping("/register/form")
    public String registerForm() {
        // Возвращает шаблон register.html для отображения формы регистрации
        return "tasks/register";
    }
}
