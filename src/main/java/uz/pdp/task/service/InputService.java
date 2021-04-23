package uz.pdp.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task.entity.Input;
import uz.pdp.task.repository.InputRepository;

import java.util.List;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    public List<Input> getInputs(Integer toCard_id){
        return inputRepository.findAllByToCardId(toCard_id);
    }
}
