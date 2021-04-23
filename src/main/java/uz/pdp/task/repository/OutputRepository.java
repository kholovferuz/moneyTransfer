package uz.pdp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
}
