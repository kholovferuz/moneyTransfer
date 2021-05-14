package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.dto.CardDTO;
import uz.pdp.task.entity.Card;
import uz.pdp.task.service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
}
