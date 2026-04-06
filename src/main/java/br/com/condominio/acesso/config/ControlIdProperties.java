package br.com.condominio.acesso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Mapeia as propriedades do equipamento iDFace
 * definidas no application.yml (prefixo: controlid.device).
 * Fornece o endereço base (IP) para as requisições HTTP.
 */

@Data
@Component
@ConfigurationProperties(prefix = "controlid.device")
public class ControlIdProperties {

    private String ip;
    private String adminUser;
    private String adminPassword;
    private int sessionTimeout;

    public String getBaseUrl() {
        return "http://" + ip;
    }
}
