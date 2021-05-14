package uz.pdp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task.entity.Card;


public interface CardRepository extends JpaRepository<Card, Integer> {
}
