package savegnago.totem.mediator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.EmployeeCard;

@Repository
public interface EmployeeCardRepo extends CrudRepository<EmployeeCard, Integer> {
}
