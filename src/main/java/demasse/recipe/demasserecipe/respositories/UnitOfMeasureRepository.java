package demasse.recipe.demasserecipe.respositories;

import org.springframework.data.repository.CrudRepository;
import demasse.recipe.demasserecipe.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findByUom(String description);
}
