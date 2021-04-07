package uz.pdp.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task.Entity.Card;
import uz.pdp.task.JWT.JWTProvider;
import uz.pdp.task.Repository.CardRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    JWTProvider jwtProvider;

    public String addCard(Card card, HttpServletRequest request) {
        Card cards = new Card();
        cards.setCardNumber(card.getCardNumber());
        cards.setBalance(card.getBalance());
        cards.setExpirationDate(card.getExpirationDate());

        String token = request.getHeader("Authorization");
        token = token.substring(7);

        cards.setUsername(jwtProvider.getUsernameFromToken(token));
        cardRepository.save(cards);
        return "Card added";
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

}
