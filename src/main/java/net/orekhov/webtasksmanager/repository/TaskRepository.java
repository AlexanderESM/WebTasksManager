package net.orekhov.webtasksmanager.repository;

import net.orekhov.webtasksmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link Task}.
 * Предоставляет базовый доступ к данным таблицы Task.
 * Наследуется от JpaRepository, что позволяет использовать стандартные операции CRUD.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository предоставляет базовые CRUD операции, дополнительные методы можно добавлять по мере необходимости
}
