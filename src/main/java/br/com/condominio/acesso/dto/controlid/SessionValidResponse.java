package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionValidResponse {

    @JsonProperty("session_is_valid")
    private boolean sessionIsValid;
}