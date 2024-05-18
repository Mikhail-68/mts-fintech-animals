package ru.mts.aspect;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.mts.annotation.Logging;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(logging) && execution(public * ru.mts..*(..))")
    public Object logging(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        Logger logger = LogManager.getLogger(joinPoint.getTarget() != null
                ? joinPoint.getTarget().getClass() : joinPoint.getThis().getClass());

        if(logging.entering()) {
            String descMethod = !logging.value().isBlank() ? logging.value() : joinPoint.getSignature().getName();
            logger.atLevel(Level.toLevel(logging.level())).log("{} >> {}", descMethod, joinPoint.getArgs());
        }

        Object result = joinPoint.proceed();

        if(logging.exiting()) {
            String descMethod = !logging.value().isBlank() ? logging.value() : joinPoint.getSignature().getName();
            logger.atLevel(Level.toLevel(logging.level())).log("{} << {}", descMethod, result);
        }

        return result;
    }
}
