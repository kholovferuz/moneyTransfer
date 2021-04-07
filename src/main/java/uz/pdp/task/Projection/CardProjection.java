package uz.pdp.task.Projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task.Entity.Card;

import java.sql.Timestamp;

@Projection(types = Card.class)
public interface CardProjection {
    Integer getId();

    String getCardholderName();

    long getCardNumber();

    Timestamp getExpirationDate();

    Double getBalance();
}
