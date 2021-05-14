package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.entity.Input;

import java.util.List;

@Service
public interface InputService {
    List<Input> getInputs(Integer toCard_id);
}
