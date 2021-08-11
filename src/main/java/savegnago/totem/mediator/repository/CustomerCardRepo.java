package savegnago.totem.mediator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.CustomerCard;

@Repository
public interface CustomerCardRepo extends CrudRepository<CustomerCard, Integer> {
}
