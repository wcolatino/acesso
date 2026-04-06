/**
 * Entidade que registra cada tentativa de acesso ao condomínio.
 * Armazena se o acesso foi concedido ou negado e o motivo,
 * servindo como histórico completo de entradas e saídas.
 */
package br.com.condominio.acesso.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "accessed_at", nullable = false)
    private LocalDateTime accessedAt;

    @Column(nullable = false)
    private Boolean granted;

    /**
     * Motivo da decisão de acesso.
     * Exemplos: GRANTED, EXPIRED, NO_RULE, INACTIVE
     */
    private String reason;

    @PrePersist
    public void prePersist() {
        this.accessedAt = LocalDateTime.now();
    }
}