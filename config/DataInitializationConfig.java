package org.example.ex5.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ex5.entity.AccountBalance;
import org.example.ex5.repository.AccountBalanceRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializationConfig {
    private final AccountBalanceRepository accountBalanceRepository;

    @Bean
    public ApplicationRunner initializeData() {
        return args -> {
            log.info("Initializing sample account data...");

            // Create sample accounts
            AccountBalance user1 = new AccountBalance();
            user1.setUsername("user1");
            user1.setCashAvailable(50000.0);

            AccountBalance user2 = new AccountBalance();
            user2.setUsername("user2");
            user2.setCashAvailable(30000.0);

            AccountBalance user3 = new AccountBalance();
            user3.setUsername("user3");
            user3.setCashAvailable(10000.0);

            accountBalanceRepository.save(user1);
            accountBalanceRepository.save(user2);
            accountBalanceRepository.save(user3);

            log.info("Sample account data initialized successfully.");
        };
    }
}

