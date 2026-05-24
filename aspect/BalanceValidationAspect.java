package org.example.ex5.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.ex5.dto.PlaceOrderRequest;
import org.example.ex5.entity.AccountBalance;
import org.example.ex5.exception.InsufficientFundsException;
import org.example.ex5.repository.AccountBalanceRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
@RequiredArgsConstructor
@Slf4j
public class BalanceValidationAspect {
    private final AccountBalanceRepository accountBalanceRepository;

    @Before("execution(* org.example.ex5.service.PlaceOrderService.placeOrder(..))")
    public void validateBalance(JoinPoint joinPoint) {
        log.info("========== BalanceValidationAspect @Order(3) ==========");
        
        Object[] arguments = joinPoint.getArgs();
        PlaceOrderRequest request = (PlaceOrderRequest) arguments[0];
        String username = (String) arguments[1];

        log.info("Validating balance for user: {}", username);

        AccountBalance accountBalance = accountBalanceRepository.findByUsername(username)
            .orElseThrow(() -> {
                log.error("Account not found for user: {}", username);
                return new InsufficientFundsException("Account not found for user: " + username);
            });

        Double requiredFunds = request.getQuantity() * request.getPrice();
        log.info("Required funds: {}, Available funds: {}", requiredFunds, accountBalance.getCashAvailable());

        if (accountBalance.getCashAvailable() < requiredFunds) {
            log.error("Insufficient funds. Required: {}, Available: {}", requiredFunds, accountBalance.getCashAvailable());
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Required: %.2f, Available: %.2f",
                    requiredFunds, accountBalance.getCashAvailable())
            );
        }

        log.info("Balance validation passed.");
        log.info("========================================================");
    }
}

