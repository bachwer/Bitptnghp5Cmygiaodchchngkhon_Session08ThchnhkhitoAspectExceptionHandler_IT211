package org.example.ex5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class LoggingAspect {
    
    @Before("execution(* org.example.ex5.service.PlaceOrderService.placeOrder(..))")
    public void logBeforePlaceOrder(JoinPoint joinPoint) {
        log.info("========== LoggingAspect @Order(1) ==========");
        log.info("Method: {}", joinPoint.getSignature().getName());
        Object[] arguments = joinPoint.getArgs();
        if (arguments.length > 0) {
            log.info("Request: {}", arguments[0]);
        }
        if (arguments.length > 1) {
            log.info("Username: {}", arguments[1]);
        }
        log.info("==========================================");
    }
}

