package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO de resposta do endpoint /session_is_valid.fcgi do iDFace.
 * Indica se o token de sessão atual ainda é válido.
 */

@Data
public class SessionValidResponse {

    @JsonProperty("session_is_valid")
    private boolean sessionIsValid;
}