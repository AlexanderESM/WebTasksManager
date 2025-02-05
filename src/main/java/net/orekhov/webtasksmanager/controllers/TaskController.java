package net.orekhov.webtasksmanager.controllers;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import net.orekhov.webtasksmanager.model.Task;
import net.orekhov.webtasksmanager.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контроллер для обработки запросов, связанных с задачами.
 * Предоставляет функциональность для создания, редактирования, удаления и отображения задач.
 */
@Controller  // Указывает, что класс является контроллером для обработки HTTP-запросов
@RequestMapping("/tasks")  // Указывает, что все маршруты начинаются с "/tasks"
public class TaskController {

    @Resource
    private TaskService taskService; // Внедрение зависимости для работы с сервисом задач

    /**
     * Отображает список всех задач.
     *
     * @param model объект для передачи данных в представление
     * @return имя шаблона для отображения списка задач
     */
    @GetMapping  // Обрабатывает GET-запросы по маршруту "/tasks"
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());  // Добавляем все задачи в модель
        return "tasks/list";  // Возвращаем шаблон для отображения списка задач
    }

    /**
     * Отображает форму для создания новой задачи.
     *
     * @param model объект для передачи данных в представление
     * @return имя шаблона для отображения формы создания задачи
     */
    @GetMapping("/create")  // Обрабатывает GET-запросы по маршруту "/tasks/create"
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());  // Создаем новый пустой объект задачи
        return "tasks/create";  // Возвращаем шаблон для формы создания задачи
    }

    /**
     * Сохраняет новую задачу.
     *
     * @param task объект задачи, полученный из формы
     * @param result результат валидации объекта задачи
     * @param redirectAttributes атрибуты для перенаправления
     * @return перенаправление на список задач в случае успешного сохранения
     */
    @PostMapping  // Обрабатывает POST-запросы для сохранения новой задачи
    public String saveTask(@Valid @ModelAttribute Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {  // Если есть ошибки валидации, возвращаем форму с ошибками
            return "tasks/error";  // Возвращаем шаблон ошибки
        }

        taskService.save(task);  // Сохраняем задачу в базе данных
        return "redirect:/tasks";  // Перенаправляем на страницу со списком задач
    }

    /**
     * Отображает форму для редактирования задачи.
     *
     * @param id идентификатор задачи, которую нужно редактировать
     * @param model объект для передачи данных в представление
     * @return имя шаблона для отображения формы редактирования задачи
     */
    @GetMapping("/edit/{id}")  // Обрабатывает GET-запросы для редактирования задачи по ее ID
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);  // Находим задачу по ID
        model.addAttribute("task", task);  // Добавляем задачу в модель
        return "tasks/edit";  // Возвращаем шаблон для редактирования задачи
    }

    /**
     * Обновляет существующую задачу.
     *
     * @param task объект задачи, полученный из формы
     * @param result результат валидации объекта задачи
     * @param id идентификатор задачи, которую нужно обновить
     * @return перенаправление на список задач в случае успешного обновления
     */
    @PostMapping("/edit/{id}")  // Обрабатывает POST-запросы для обновления задачи по ID
    public String updateTask(@Valid @ModelAttribute Task task, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {  // Если есть ошибки валидации, возвращаем форму с ошибками
            return "tasks/error";  // Возвращаем шаблон ошибки
        }

        task.setId(id);  // Устанавливаем ID для обновления задачи
        taskService.save(task);  // Обновляем задачу в базе данных
        return "redirect:/tasks";  // Перенаправляем на страницу со списком задач
    }

    /**
     * Удаляет задачу по ее ID.
     *
     * @param id идентификатор задачи, которую нужно удалить
     * @return перенаправление на список задач после удаления
     */
    @GetMapping("/delete/{id}")  // Обрабатывает GET-запросы для удаления задачи по ID
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);  // Удаляем задачу по ID
        return "redirect:/tasks";  // Перенаправляем на страницу со списком задач
    }
}
