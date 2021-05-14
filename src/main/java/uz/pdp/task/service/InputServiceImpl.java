package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.entity.Input;
import uz.pdp.task.repository.InputRepository;

import java.util.List;

@Service
public class InputServiceImpl implements InputService {

    final InputRepository inputRepository;

    public InputServiceImpl(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public List<Input> getInputs(Integer toCard_id){
        return inputRepository.findAllByToCardId(toCard_id);
    }
}
