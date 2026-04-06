/**
 * DTO de resposta do endpoint /load_objects.fcgi para o objeto "users".
 * Representa a lista de usuários cadastrados no equipamento iDFace.
 */
package br.com.condominio.acesso.dto.controlid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceUserResponse {

    @JsonProperty("users")
    private List<DeviceUser> users;

    @Data
    public static class DeviceUser {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("registration")
        private String registration;

        @JsonProperty("password")
        private String password;

        @JsonProperty("salt")
        private String salt;
    }
}