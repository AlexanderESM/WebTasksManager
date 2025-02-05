package net.orekhov.webtasksmanager.exceptions;

/**
 * Исключение, которое используется для обработки ошибок, связанных с некорректными задачами.
 * Например, это может быть использовано для проверки данных задачи перед её сохранением или редактированием.
 */
public class InvalidTaskException extends RuntimeException {

    // Конструктор, принимающий сообщение об ошибке
    public InvalidTaskException(String message) {
        super(message);  // Передаем сообщение в родительский класс RuntimeException
    }
}
