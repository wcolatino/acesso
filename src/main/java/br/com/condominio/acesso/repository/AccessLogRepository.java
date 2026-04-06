/**
 * Repositório JPA para a entidade AccessLog.
 * Fornece consultas para histórico de acessos por pessoa
 * e para relatórios de entradas e saídas do condomínio.
 */
package br.com.condominio.acesso.repository;

import br.com.condominio.acesso.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLog> findByPersonId(Long personId);

    List<AccessLog> findByAccessedAtBetween(LocalDateTime start, LocalDateTime end);

    List<AccessLog> findByGrantedFalse();
}