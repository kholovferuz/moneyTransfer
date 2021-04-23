package uz.pdp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task.entity.Input;

import java.util.List;

public interface InputRepository extends JpaRepository<Input, Integer> {
    List<Input> findAllByToCardId(Integer toCard_id);
}
