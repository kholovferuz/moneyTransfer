package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.dto.CardDTO;
import uz.pdp.task.entity.Card;
import uz.pdp.task.jwt.JWTProvider;
import uz.pdp.task.repository.CardRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    final CardRepository cardRepository;
    final JWTProvider jwtProvider;

    public CardServiceImpl(CardRepository cardRepository,
                           JWTProvider jwtProvider) {
        this.cardRepository = cardRepository;
        this.jwtProvider = jwtProvider;
    }

    public String addCard(CardDTO cardDTO, HttpServletRequest request) {
        boolean existsByCardNumber = cardRepository.existsByCardNumber(cardDTO.getCardNumber());
        if (existsByCardNumber) {
            return "Card with this number already exists";
        }
        Card cards = new Card();
        cards.setCardNumber(cardDTO.getCardNumber());
        cards.setBalance(cardDTO.getBalance());
        cards.setExpiryDate(LocalDate.now().plusYears(5));
        cards.setCardholderName(cardDTO.getCardholerName());

        String token = request.getHeader("Authorization");
        token = token.substring(7);

        cards.setUsername(jwtProvider.getUsernameFromToken(token));
        cardRepository.save(cards);
        return "Card added";
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

}
