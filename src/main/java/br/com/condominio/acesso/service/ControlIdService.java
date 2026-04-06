/**
 * Serviço central de comunicação com o equipamento iDFace.
 * Responsável por enviar todas as requisições HTTP para a API REST do equipamento.
 * Utiliza o SessionService para garantir que sempre há uma sessão válida
 * antes de realizar qualquer operação.
 */
package br.com.condominio.acesso.service;

import br.com.condominio.acesso.config.ControlIdProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ControlIdService {

    private final ControlIdProperties properties;
    private final SessionService sessionService;
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private HttpEntity<String> buildEntity(Object body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String json = body != null ? objectMapper.writeValueAsString(body) : "{}";
            log.debug("Payload: {}", json);
            return new HttpEntity<>(json, headers);
        } catch (Exception e) {
            log.error("Erro ao serializar payload: {}", e.getMessage());
            return new HttpEntity<>("{}", new HttpHeaders());
        }
    }

    private String buildUrl(String endpoint) {
        return properties.getBaseUrl() + endpoint + "?session=" + sessionService.getSession();
    }

    public <T> T post(String endpoint, Object request, Class<T> responseType) {
        try {
            String url = buildUrl(endpoint);
            return restTemplate.postForObject(url, buildEntity(request), responseType);
        } catch (Exception e) {
            log.warn("Erro na requisição para {}. Tentando renovar sessão...", endpoint);
            sessionService.login();
            try {
                String url = buildUrl(endpoint);
                return restTemplate.postForObject(url, buildEntity(request), responseType);
            } catch (Exception ex) {
                log.error("Erro após renovar sessão para {}: {}", endpoint, ex.getMessage());
                return null;
            }
        }
    }
}