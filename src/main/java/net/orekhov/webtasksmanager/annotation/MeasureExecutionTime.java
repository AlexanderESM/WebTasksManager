package net.orekhov.webtasksmanager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для измерения времени выполнения метода.
 * <p>
 * Эта аннотация применяется к методам и может быть использована
 * в сочетании с AOP (Aspect-Oriented Programming) для логирования
 * времени их выполнения.
 * </p>
 *
 * @author [Ваше Имя]
 */
@Target(ElementType.METHOD) // Аннотация может быть применена только к методам
@Retention(RetentionPolicy.RUNTIME) // Аннотация доступна во время выполнения
public @interface MeasureExecutionTime {
}
