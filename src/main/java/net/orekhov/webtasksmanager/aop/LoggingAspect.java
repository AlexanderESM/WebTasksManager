package net.orekhov.webtasksmanager.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

/**
 * Аспект, отвечающий за логирование действий в сервисе задач (TaskService).
 * Логирование выполняется при запуске методов и при выбросе исключений.
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    /**
     * Логирование начала работы метода в сервисе задач.
     * Этот метод срабатывает перед выполнением любого метода из класса TaskService.
     *
     * @param joinPoint информация о вызванном методе
     */
    @Before("execution(* net.orekhov.webtasksmanager.services.TaskService.*(..))")
    public void logMethodExecution(JoinPoint joinPoint) {
        log.info("Запущена работа метода: {}", joinPoint.getSignature().getName());
    }

    /**
     * Логирование исключений, выброшенных методами из TaskService.
     * Этот метод срабатывает после того, как метод из TaskService выбросил исключение.
     *
     * @param exception выброшенное исключение
     * @param joinPoint информация о методе, вызвавшем исключение
     */
    @AfterThrowing(value = "execution(* net.orekhov.webtasksmanager.services.TaskService.*(..))",
            throwing = "exception", argNames = "exception,joinPoint")
    public void logException(Exception exception, JoinPoint joinPoint) {
        log.info("Метод {} породил ошибку: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
