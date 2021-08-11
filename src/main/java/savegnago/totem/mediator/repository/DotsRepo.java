package savegnago.totem.mediator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.Dots;

import java.util.Optional;

@Repository
public interface DotsRepo extends CrudRepository<Dots, Integer> {

	@Query(value = "SELECT COUNT(1) = 1 FROM Dots d WHERE d.cpf = :cpf")
	boolean existByCpf(@Param(value = "cpf") final String cpf);

	@Query(value = "SELECT d FROM Dots d WHERE d.cpf = :cpf")
	Optional<Dots> findByCpf(@Param(value = "cpf") final String cpf);
}
