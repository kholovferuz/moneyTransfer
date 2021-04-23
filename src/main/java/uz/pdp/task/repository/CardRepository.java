package uz.pdp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task.entity.Card;
import uz.pdp.task.projection.CardProjection;

@RepositoryRestResource(path = "card", excerptProjection = CardProjection.class)
public interface CardRepository extends JpaRepository<Card, Integer> {
}
