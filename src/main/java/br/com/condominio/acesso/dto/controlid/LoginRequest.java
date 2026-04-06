package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de requisição para o endpoint /login.fcgi do iDFace.
 * Contém as credenciais de autenticação (login e senha).
 */

@Data
@AllArgsConstructor
public class LoginRequest {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;
}