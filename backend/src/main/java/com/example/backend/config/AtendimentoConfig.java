package com.example.backend.config;

import com.example.backend.patterns.decorator.AtendimentoServiceLoggerDecorator;
import com.example.backend.patterns.decorator.IAtendimentoService;
import com.example.backend.service.AtendimentoServiceImpl;
import com.example.backend.util.AtendimentoLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AtendimentoConfig {

    @Bean
    public AtendimentoLogger atendimentoLogger() {
        return new AtendimentoLogger();
    }

    @Bean
    @Primary
    public IAtendimentoService atendimentoService(
            AtendimentoServiceImpl impl,
            AtendimentoLogger logger
    ) {
        return new AtendimentoServiceLoggerDecorator(impl, logger);
    }
}