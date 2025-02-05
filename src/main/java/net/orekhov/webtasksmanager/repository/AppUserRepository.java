package net.orekhov.webtasksmanager.repository;

import net.orekhov.webtasksmanager.model.jwt_authentication.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link AppUser}.
 * Содержит методы для поиска пользователей по имени.
 */
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    /**
     * Находит пользователя по имени.
     *
     * @param username Имя пользователя для поиска
     * @return Optional с найденным пользователем или пустой, если не найден
     */
    Optional<AppUser> findByUsername(String username);
}
