package net.orekhov.webtasksmanager.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Конфигурационный класс для включения поддержки аспектно-ориентированного программирования (AOP) в приложении.
 * Этот класс позволяет использовать аннотации @Aspect и другие аспекты в проекте.
 *
 * @Configuration указывает, что данный класс является конфигурационным для Spring.
 * @EnableAspectJAutoProxy включает автоматическое проксирование аспектов для работы с AOP.
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    // Данный класс может быть расширен для добавления собственных аспектов и конфигураций.
}
