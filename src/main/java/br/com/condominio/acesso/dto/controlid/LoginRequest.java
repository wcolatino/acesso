/**
 * DTO de requisição para o endpoint /login.fcgi do iDFace.
 * Contém as credenciais de autenticação (login e senha).
 */
package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;
}