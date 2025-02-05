package net.orekhov.webtasksmanager.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Глобальный обработчик ошибок, который перехватывает исключения
 * и перенаправляет пользователя на соответствующие страницы с сообщениями об ошибках.
 */
@ControllerAdvice  // Указывает, что это глобальный обработчик исключений
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение, если задача не найдена.
     *
     * @param ex исключение TaskNotFoundException
     * @param redirectAttributes атрибуты для перенаправления
     * @return перенаправление на список задач с сообщением об ошибке
     */
    @ExceptionHandler(TaskNotFoundException.class)  // Обрабатывает исключения TaskNotFoundException
    public String handleTaskNotFound(TaskNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());  // Добавляем сообщение об ошибке
        return "redirect:/tasks";  // Перенаправляем пользователя на страницу списка задач
    }

    /**
     * Обрабатывает исключение для некорректных задач (например, с неправильными данными).
     *
     * @param ex исключение InvalidTaskException
     * @param model объект модели для передачи данных в представление
     * @return перенаправление на страницу создания задачи с сообщением об ошибке
     */
    @ExceptionHandler(InvalidTaskException.class)  // Обрабатывает исключения InvalidTaskException
    public String handleInvalidTask(InvalidTaskException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());  // Добавляем сообщение об ошибке
        return "tasks/create";  // Перенаправляем на форму для создания задачи с ошибкой
    }

    /**
     * Обрабатывает любые другие неожиданные ошибки, не относящиеся к задачам.
     *
     * @param ex общее исключение
     * @param model объект модели для передачи данных в представление
     * @return страницу ошибки с сообщением
     */
    @ExceptionHandler(Exception.class)  // Обрабатывает все остальные исключения
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());  // Сообщение об ошибке
        return "error";  // Перенаправляем на страницу с ошибкой
    }
}
