package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.dto.OutputDTO;
import uz.pdp.task.service.OutputService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/output")
public class OutputController {

    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public HttpEntity<?> transferMoney(@Valid @RequestBody OutputDTO outputDTO, HttpServletRequest request) {
        String moneyToCard = outputService.tranferMoneyToCard(outputDTO, request);
        return ResponseEntity.status(202).body(moneyToCard);
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
