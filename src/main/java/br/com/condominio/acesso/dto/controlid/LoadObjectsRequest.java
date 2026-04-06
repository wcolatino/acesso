/**
 * DTO de requisição para o endpoint /load_objects.fcgi do iDFace.
 * Utilizado para consultar objetos cadastrados no equipamento,
 * como usuários, cartões, logs, etc.
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
public class LoadObjectsRequest {

    @JsonProperty("object")
    private String object;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("offset")
    private Integer offset;
}