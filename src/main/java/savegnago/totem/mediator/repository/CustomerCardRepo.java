package savegnago.totem.mediator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.CustomerCard;

@Repository
public interface CustomerCardRepo extends CrudRepository<CustomerCard, Integer> {

	@Query(value = "SELECT COUNT(1) = 1 FROM CustomerCard cc WHERE cc.cardNumber = :card_number")
	boolean existsByCardNumber(@Param(value = "card_number") final String cardNumber);
}
