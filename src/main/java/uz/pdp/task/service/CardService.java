package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.dto.CardDTO;
import uz.pdp.task.entity.Card;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface CardService {

    String addCard(CardDTO cardDTO, HttpServletRequest request);

    List<Card> getAllCards();
}
