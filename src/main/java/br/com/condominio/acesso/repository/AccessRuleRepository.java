/**
 * Repositório JPA para a entidade AccessRule.
 * Fornece consultas para verificar regras de acesso ativas
 * de uma pessoa, utilizado na decisão de liberar ou negar acesso.
 */
package br.com.condominio.acesso.repository;

import br.com.condominio.acesso.model.AccessRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRuleRepository extends JpaRepository<AccessRule, Long> {

    List<AccessRule> findByPersonIdAndActiveTrue(Long personId);
}