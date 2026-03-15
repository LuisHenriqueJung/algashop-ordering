package com.jung.algashop.ordering.infrastructure.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing(
        auditorAwareRef = "auditorAware",
        dateTimeProviderRef = "auditDateTimeProvider"

)
@Configuration
public class SpringDataAuditing {

    @Bean
    public DateTimeProvider auditDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
    }

    @Bean
    public AuditorAware<UUID> auditorAware() {
        return () -> Optional.of(UUID.randomUUID());
    }
}
