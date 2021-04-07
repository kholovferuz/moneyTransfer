package uz.pdp.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task.Entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
}
