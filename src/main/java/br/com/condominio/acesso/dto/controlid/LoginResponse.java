package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponse {

    @JsonProperty("session")
    private String session;
}