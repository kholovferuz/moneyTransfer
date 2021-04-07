package uz.pdp.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task.Entity.Card;
import uz.pdp.task.Projection.CardProjection;

@RepositoryRestResource(path = "card", excerptProjection = CardProjection.class)
public interface CardRepository extends JpaRepository<Card, Integer> {
}
