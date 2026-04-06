package br.com.condominio.acesso.service;

import br.com.condominio.acesso.config.ControlIdProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Serviço central de comunicação com o equipamento iDFace.
 * Responsável por enviar todas as requisições HTTP para a API REST do equipamento.
 * Utiliza o SessionService para garantir que sempre há uma sessão válida
 * antes de realizar qualquer operação.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ControlIdService {

    private final ControlIdProperties properties;
    private final SessionService sessionService;
    private final RestTemplate restTemplate;

    /**
     * Monta a URL completa para um endpoint do iDFace
     * já incluindo o token de sessão como parâmetro.
     *
     * @param endpoint ex: /load_objects.fcgi
     * @return URL completa com sessão
     */
    private String buildUrl(String endpoint) {
        return properties.getBaseUrl() + endpoint + "?session=" + sessionService.getSession();
    }

    /**
     * Realiza um POST genérico para a API do iDFace.
     * Caso a sessão esteja expirada, tenta renovar e reenviar uma vez.
     *
     * @param endpoint  endpoint do iDFace ex: /save_objects.fcgi
     * @param request   objeto a ser serializado como JSON no corpo da requisição
     * @param responseType classe esperada na resposta
     * @return resposta deserializada ou null em caso de erro
     */
    public <T> T post(String endpoint, Object request, Class<T> responseType) {
        try {
            String url = buildUrl(endpoint);
            log.debug("POST {} | body: {}", url, request);
            return restTemplate.postForObject(url, request, responseType);
        } catch (Exception e) {
            log.warn("Erro na requisição para {}. Tentando renovar sessão...", endpoint);
            sessionService.login();
            try {
                String url = buildUrl(endpoint);
                return restTemplate.postForObject(url, request, responseType);
            } catch (Exception ex) {
                log.error("Erro após renovar sessão para {}: {}", endpoint, ex.getMessage());
                return null;
            }
        }
    }
}