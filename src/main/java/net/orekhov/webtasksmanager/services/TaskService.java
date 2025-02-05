package net.orekhov.webtasksmanager.services;

import jakarta.annotation.Resource;
import net.orekhov.webtasksmanager.annotation.MeasureExecutionTime;
import net.orekhov.webtasksmanager.exceptions.InvalidTaskException;
import net.orekhov.webtasksmanager.exceptions.TaskNotFoundException;
import net.orekhov.webtasksmanager.model.Task;
import net.orekhov.webtasksmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с задачами.
 * Предоставляет методы для получения, сохранения, удаления задач, а также для работы с исключениями.
 */
@Service  // Аннотация для обозначения сервиса, который инкапсулирует логику обработки задач.
public class TaskService {

    @Resource  // Внедрение зависимости TaskRepository для работы с данными задач.
    private TaskRepository taskRepository;

    /**
     * Получает список всех задач из репозитория.
     *
     * @return Список всех задач.
     */
    @MeasureExecutionTime  // Аннотация для измерения времени выполнения метода.
    public List<Task> findAll() {
        return taskRepository.findAll();  // JpaRepository автоматически находит все записи в таблице.
    }

    /**
     * Находит задачу по ID.
     *
     * @param id Идентификатор задачи.
     * @return Найденная задача.
     * @throws TaskNotFoundException Если задача с таким ID не найдена.
     */
    @MeasureExecutionTime  // Аннотация для измерения времени выполнения метода.
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));  // Исключение, если задача не найдена.
    }

    /**
     * Сохраняет новую задачу.
     * Проверяется, что заголовок задачи не пустой.
     *
     * @param task Задача для сохранения.
     * @return Сохраненная задача.
     * @throws InvalidTaskException Если заголовок задачи пуст.
     */
    @MeasureExecutionTime  // Аннотация для измерения времени выполнения метода.
    public Task save(Task task) {
        // Проверка на пустой заголовок задачи.
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new InvalidTaskException("Task title cannot be empty");  // Исключение, если заголовок пустой.
        }
        return taskRepository.save(task);  // Сохранение задачи в БД.
    }

    /**
     * Удаляет задачу по ID.
     *
     * @param id Идентификатор задачи для удаления.
     * @throws TaskNotFoundException Если задача с таким ID не найдена.
     */
    @MeasureExecutionTime  // Аннотация для измерения времени выполнения метода.
    public void deleteById(Long id) {
        // Проверка существования задачи в базе данных перед удалением.
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);  // Исключение, если задача не существует.
        }
        taskRepository.deleteById(id);  // Удаление задачи из базы данных.
    }
}
