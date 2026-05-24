package org.example.ex5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.ex5.exception.MarketClosedException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
@Order(2)
@Slf4j
public class TradingTimeAspect {
    private static final LocalTime MARKET_OPEN = LocalTime.of(9, 0); // 09:00
    private static final LocalTime MARKET_CLOSE = LocalTime.of(15, 0); // 15:00

    @Before("execution(* org.example.ex5.service.PlaceOrderService.placeOrder(..))")
    public void checkTradingTime(JoinPoint joinPoint) {
        log.info("========== TradingTimeAspect @Order(2) ==========");
        LocalTime currentTime = LocalTime.now();
        log.info("Current time: {}", currentTime);

        if (currentTime.isBefore(MARKET_OPEN) || currentTime.isAfter(MARKET_CLOSE)) {
            log.error("Market is closed. Trading hours are 09:00 to 15:00. Current time: {}", currentTime);
            throw new MarketClosedException(
                String.format("Market is closed. Trading hours are 09:00 to 15:00. Current time: %s", currentTime)
            );
        }

        log.info("Market is open. Proceeding with order placement.");
        log.info("================================================");
    }
}

