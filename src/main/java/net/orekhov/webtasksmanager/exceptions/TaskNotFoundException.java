package net.orekhov.webtasksmanager.exceptions;

/**
 * Исключение, которое используется, когда задача с указанным ID не найдена.
 * Это исключение выбрасывается в случае, если попытка найти задачу по ID не дала результатов.
 */
public class TaskNotFoundException extends RuntimeException {

    // Конструктор, принимающий ID задачи
    public TaskNotFoundException(Long id) {
        // Формируем сообщение об ошибке с ID задачи
        super("Task with ID " + id + " not found");
    }
}
