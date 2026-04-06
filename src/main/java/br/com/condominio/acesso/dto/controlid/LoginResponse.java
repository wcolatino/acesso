package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO de resposta do endpoint /login.fcgi do iDFace.
 * Contém o token de sessão retornado pelo equipamento.
 */

@Data
public class LoginResponse {

    @JsonProperty("session")
    private String session;
}