/**
 * Enum que representa os tipos de pessoas
 * que podem ter acesso ao condomínio.
 */
package br.com.condominio.acesso.model;

public enum PersonType {
    RESIDENT,       // Morador
    VISITOR,        // Visitante (acesso temporário)
    SERVICE,        // Prestador de serviço
    EMPLOYEE        // Funcionário
}