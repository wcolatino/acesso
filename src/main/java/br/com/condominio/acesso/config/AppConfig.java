/**
 * Classe de configuração geral da aplicação.
 * Registra o RestTemplate como bean do Spring,
 * utilizado para as requisições HTTP ao equipamento iDFace.
 */
package br.com.condominio.acesso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}