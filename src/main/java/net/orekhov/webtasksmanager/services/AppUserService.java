package net.orekhov.webtasksmanager.services;

import jakarta.annotation.Resource;
import net.orekhov.webtasksmanager.model.jwt_authentication.AppUser;
import net.orekhov.webtasksmanager.model.jwt_authentication.AppUserDto;
import net.orekhov.webtasksmanager.model.jwt_authentication.Role;
import net.orekhov.webtasksmanager.model.jwt_authentication.util.AuthDto;
import net.orekhov.webtasksmanager.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Аннотация, указывающая, что это сервисный класс, который управляет логикой работы с пользователями.
public class AppUserService {

    @Resource  // Внедрение зависимостей: инстанс репозитория для работы с пользователями
    private AppUserRepository appUserRepository;

    @Resource  // Внедрение зависимостей: инстанс кодировщика паролей
    private PasswordEncoder passwordEncoder;

    /**
     * Метод для регистрации нового пользователя.
     * Пароль пользователя кодируется перед сохранением.
     * После этого создается объект AppUserDto и сохраняется в репозитории.
     *
     * @param authDto DTO, содержащий информацию для регистрации пользователя
     */
    public void registerAppUser(AuthDto authDto) {
        // Кодируем пароль с использованием PasswordEncoder
        authDto.setPassword(passwordEncoder.encode(authDto.getPassword()));

        // Создаем объект AppUserDto с информацией о пользователе и его ролях
        AppUserDto appUserDto = AppUserDto.builder()
                .username(authDto.getUsername())  // Устанавливаем имя пользователя
                .password(authDto.getPassword())  // Устанавливаем закодированный пароль
                .roles(List.of(Role.USER))  // Устанавливаем роль "USER"
                .build();

        // Используем ModelMapper для преобразования AppUserDto в сущность AppUser и сохраняем в репозитории
        appUserRepository.save(new ModelMapper().map(appUserDto, AppUser.class));
    }
}
