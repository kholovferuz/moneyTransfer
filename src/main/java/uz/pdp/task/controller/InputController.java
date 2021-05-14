package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task.entity.Input;
import uz.pdp.task.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping("/{toCard_id}")
    public HttpEntity<?> getInputs(@PathVariable Integer toCard_id){
        List<Input> inputs = inputService.getInputs(toCard_id);
        return ResponseEntity.ok(inputs);
    }

}
