/**
 * Entidade que representa as regras de acesso de uma pessoa.
 * Permite definir períodos de validade, dias da semana
 * e horários permitidos — ideal para visitantes e prestadores.
 */
package br.com.condominio.acesso.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "access_rules")
public class AccessRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;

    /**
     * Data de expiração do acesso.
     * Null indica que o acesso não expira (ex: moradores).
     */
    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    /**
     * Dias da semana permitidos.
     * Exemplo: "MON,TUE,WED,THU,FRI"
     * Null indica que todos os dias são permitidos.
     */
    @Column(name = "days_of_week")
    private String daysOfWeek;

    @Column(name = "time_from")
    private LocalTime timeFrom;

    @Column(name = "time_until")
    private LocalTime timeUntil;

    @Column(nullable = false)
    private Boolean active = true;
}