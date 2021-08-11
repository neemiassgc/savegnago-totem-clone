package savegnago.totem.mediator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savegnago.totem.mediator.entity.Dots;

@Repository
public interface DotsRepo extends CrudRepository<Dots, Integer> {
}
