package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.dto.CardDTO;
import uz.pdp.task.entity.Card;
import uz.pdp.task.service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {

    final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/addNew")
    public HttpEntity<?> addNewCard(@Valid @RequestBody CardDTO cardDTO, HttpServletRequest request) {
        String addCard = cardService.addCard(cardDTO, request);
        return ResponseEntity.status(201).body(addCard);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> readCards() {
        List<Card> allCards = cardService.getAllCards();
        return ResponseEntity.ok(allCards);
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
