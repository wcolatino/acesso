/**
 * Repositório JPA para a entidade Person.
 * Fornece operações de consulta para buscar pessoas
 * por ID do dispositivo, tipo e status de atividade.
 */
package br.com.condominio.acesso.repository;

import br.com.condominio.acesso.model.Person;
import br.com.condominio.acesso.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByDeviceId(Long deviceId);

    List<Person> findByType(PersonType type);

    List<Person> findByActiveTrue();

    List<Person> findByTypeAndActiveTrue(PersonType type);
}