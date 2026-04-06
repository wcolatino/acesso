/**
 * Controller responsável pelas operações diretas
 * com o equipamento iDFace, como consulta de usuários
 * cadastrados, status da sessão e informações do dispositivo.
 */
package br.com.condominio.acesso.controller;

import br.com.condominio.acesso.dto.controlid.DeviceUserResponse;
import br.com.condominio.acesso.dto.controlid.LoadObjectsRequest;
import br.com.condominio.acesso.service.ControlIdService;
import br.com.condominio.acesso.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {

    private final ControlIdService controlIdService;
    private final SessionService sessionService;

    /**
     * Retorna o status da sessão atual com o iDFace.
     */
    @GetMapping("/session")
    public ResponseEntity<String> sessionStatus() {
        boolean valid = sessionService.isSessionValid();
        return ResponseEntity.ok(valid ? "Sessão ativa ✅" : "Sessão inválida ❌");
    }

    /**
     * Busca todos os usuários cadastrados no equipamento iDFace.
     */
    @GetMapping("/users")
    public ResponseEntity<DeviceUserResponse> getDeviceUsers(
            @RequestParam(defaultValue = "1000") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        try {
            LoadObjectsRequest request = new LoadObjectsRequest("users", limit, offset);

            log.info("Buscando usuários no iDFace | limit: {} offset: {}", limit, offset);

            DeviceUserResponse response = controlIdService.post(
                    "/load_objects.fcgi", request, DeviceUserResponse.class
            );

            log.info("Resposta do iDFace: {}", response);

            if (response == null) {
                log.error("Resposta nula do iDFace");
                return ResponseEntity.internalServerError().build();
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Erro ao buscar usuários no iDFace: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}