package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.entity.Input;
import uz.pdp.task.service.InputService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // EXEPTION HANDLER
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, String> mistakes = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mistakes.put(fieldName, errorMessage);
        });
        return mistakes;
    }

}
