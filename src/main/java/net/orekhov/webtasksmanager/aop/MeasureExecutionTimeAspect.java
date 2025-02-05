package net.orekhov.webtasksmanager.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

/**
 * Аспект для измерения времени выполнения методов, помеченных аннотацией {@code @MeasureExecutionTime}.
 * Этот аспект позволяет отслеживать продолжительность выполнения методов.
 */
@Component
@Aspect
@Slf4j
public class MeasureExecutionTimeAspect {

    /**
     * Измеряет время выполнения метода, помеченного аннотацией {@code @MeasureExecutionTime}.
     * Этот метод выполняет целевой метод и логирует время его выполнения.
     *
     * @param joinPoint объект {@link org.aspectj.lang.ProceedingJoinPoint}, содержащий информацию о методе
     * @return результат выполнения целевого метода
     * @throws Throwable если исходный метод выбросит исключение
     */
    @Around("@annotation(net.orekhov.webtasksmanager.annotation.MeasureExecutionTime)")
    public Object measureTime(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        // Засекаем время начала выполнения метода
        long startTime = System.currentTimeMillis();

        // Выполняем метод
        Object result = joinPoint.proceed();

        // Засекаем время окончания выполнения метода
        long endTime = System.currentTimeMillis();

        // Логируем время выполнения метода
        log.info("Метод {} был выполнен за: {} мс", joinPoint.getSignature().getName(), endTime - startTime);

        // Возвращаем результат выполнения метода
        return result;
    }
}
