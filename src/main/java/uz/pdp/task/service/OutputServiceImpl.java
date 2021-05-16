package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.dto.OutputDTO;
import uz.pdp.task.entity.Card;
import uz.pdp.task.entity.Input;
import uz.pdp.task.entity.Output;
import uz.pdp.task.jwt.JWTProvider;
import uz.pdp.task.repository.CardRepository;
import uz.pdp.task.repository.InputRepository;
import uz.pdp.task.repository.OutputRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class OutputServiceImpl implements OutputService {

    final OutputRepository outputRepository;
    final InputRepository inputRepository;
    final CardRepository cardRepository;
    final JWTProvider jwtProvider;

    public OutputServiceImpl(OutputRepository outputRepository,
                             InputRepository inputRepository,
                             CardRepository cardRepository,
                             JWTProvider jwtProvider) {
        this.outputRepository = outputRepository;
        this.inputRepository = inputRepository;
        this.cardRepository = cardRepository;
        this.jwtProvider = jwtProvider;
    }

    public String tranferMoneyToCard(OutputDTO outputDTO, HttpServletRequest request) {

        // request
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String usernameFromToken = jwtProvider.getUsernameFromToken(token);

        // check fromCard
        Optional<Card> optionalCard = cardRepository.findById(outputDTO.getFromCardId());
        if (optionalCard.isEmpty()) {
            return "Card with this id is not found";
        }

        // check toCard
        Optional<Card> cardOptional = cardRepository.findById(outputDTO.getToCardId());
        if (cardOptional.isEmpty()) {
            return "Card with this id is not found";
        }

        if (optionalCard.equals(cardOptional)) {
            return "Chosen cards are the same. Please, choose different cards!!!";
        }

        // catching two cards by id
        Card fromCard = optionalCard.get();
        Card toCard = cardOptional.get();

        //        from card username = token username
        if (!usernameFromToken.equals(fromCard.getUsername())) {
            return "It is not your card!";
        }


        // CODE FOR TRANSFERING MONEY

        // amount to transfer
        double amount = outputDTO.getAmount();

        // 1% commission
        double commissionAmount = amount * 0.01;

        // total amount with commission
        double amountWithCommission = commissionAmount + amount;


        // balance in the card 1
        double fromBalance = fromCard.getBalance();

        // balance in the card 2
        double toBalance = toCard.getBalance();

        if (amountWithCommission > fromBalance) {
            return "You do not have enough money";
        }

        // setting new balances to the cards after transfer
        fromBalance -= amountWithCommission;
        toBalance += amount;

        // save to the repository
        toCard.setBalance(toBalance);
        fromCard.setBalance(fromBalance);
        cardRepository.save(fromCard);
        cardRepository.save(toCard);

        Output output = new Output();
        Input input = new Input();

        output.setFromCard(fromCard);
        output.setToCard(toCard);
        output.setComissionAmount(commissionAmount);
        output.setAmount(amount);
        outputRepository.save(output);

        input.setFromCard(fromCard);
        input.setToCard(toCard);
        input.setAmount(amount);
        inputRepository.save(input);

        return "Money transfer has been successfully completed" + "\nCard: ************" + toCard.getCardNumber().substring(12)
                + "\nTime: " + new Date() + "\nAmount: " + amount + "\nCommission: " + commissionAmount + "\nTotal amount: " +
                amountWithCommission + "\nBalance: " + fromBalance;


    }
}
