package uz.pdp.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task.Entity.Input;
import uz.pdp.task.Repository.InputRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    public List<Input> getInputs(Integer toCard_id){
        return inputRepository.findAllByToCardId(toCard_id);
    }
}
