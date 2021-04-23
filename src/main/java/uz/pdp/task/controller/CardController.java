package uz.pdp.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.entity.Card;
import uz.pdp.task.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> addNewCard(@RequestBody Card card, HttpServletRequest request) {
        String addCard = cardService.addCard(card, request);
        return ResponseEntity.status(201).body(addCard);
    }

    @GetMapping
    public HttpEntity<?> readCards() {
        List<Card> allCards = cardService.getAllCards();
        return ResponseEntity.ok(allCards);
    }
}
