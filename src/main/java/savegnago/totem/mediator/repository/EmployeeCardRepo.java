package savegnago.totem.mediator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.EmployeeCard;

@Repository
public interface EmployeeCardRepo extends CrudRepository<EmployeeCard, Integer> {

	@Query(value = "SELECT COUNT(1) = 1 FROM EmployeeCard ec WHERE ec.cpf = :cpf")
	public boolean existByCpf(@Param(value = "cpf") final String cpf);
}
