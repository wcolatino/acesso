/**
 * Entidade base que representa qualquer pessoa
 * cadastrada no sistema de controle de acesso.
 * Armazena os dados principais e o ID sincronizado
 * com o equipamento iDFace.
 */
package br.com.condominio.acesso.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID do usuário sincronizado no equipamento iDFace.
     * Utilizado para identificar o usuário nas requisições à API.
     */
    @Column(name = "device_id", unique = true)
    private Long deviceId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonType type;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}