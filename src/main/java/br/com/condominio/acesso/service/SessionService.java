package br.com.condominio.acesso.service;

import br.com.condominio.acesso.config.ControlIdProperties;
import br.com.condominio.acesso.dto.controlid.LoginRequest;
import br.com.condominio.acesso.dto.controlid.LoginResponse;
import br.com.condominio.acesso.dto.controlid.SessionValidResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Serviço responsável pelo gerenciamento da sessão com o equipamento iDFace.
 * Realiza login, logout e verificação de validade da sessão.
 * Renova automaticamente a sessão em intervalos configurados
 * via application.yml (controlid.device.session-timeout).
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final ControlIdProperties properties;
    private final RestTemplate restTemplate;

    private String currentSession;

    public String getSession() {
        if (currentSession == null) {
            login();
        }
        return currentSession;
    }

    public void login() {
        try {
            String url = properties.getBaseUrl() + "/login.fcgi";

            LoginRequest request = new LoginRequest(
                    properties.getAdminUser(),
                    properties.getAdminPassword()
            );

            LoginResponse response = restTemplate.postForObject(
                    url, request, LoginResponse.class
            );

            if (response != null && response.getSession() != null) {
                currentSession = response.getSession();
                log.info("Sessão iniciada com sucesso: {}", currentSession);
            } else {
                log.error("Falha ao iniciar sessão — resposta inválida do equipamento");
            }

        } catch (Exception e) {
            log.error("Erro ao fazer login no iDFace: {}", e.getMessage());
        }
    }

    public void logout() {
        try {
            String url = properties.getBaseUrl() + "/logout.fcgi?session=" + currentSession;
            restTemplate.postForObject(url, null, Void.class);
            currentSession = null;
            log.info("Sessão encerrada com sucesso");
        } catch (Exception e) {
            log.error("Erro ao fazer logout no iDFace: {}", e.getMessage());
        }
    }

    public boolean isSessionValid() {
        try {
            String url = properties.getBaseUrl() + "/session_is_valid.fcgi?session=" + currentSession;

            SessionValidResponse response = restTemplate.postForObject(
                    url, null, SessionValidResponse.class
            );

            return response != null && response.isSessionIsValid();

        } catch (Exception e) {
            log.error("Erro ao verificar sessão: {}", e.getMessage());
            return false;
        }
    }

    @Scheduled(fixedDelayString = "${controlid.device.session-timeout}000")
    public void renovarSessao() {
        log.info("Verificando sessão com o iDFace...");
        if (currentSession == null || !isSessionValid()) {
            log.info("Sessão inválida ou expirada, renovando...");
            login();
        } else {
            log.info("Sessão ainda válida.");
        }
    }
}