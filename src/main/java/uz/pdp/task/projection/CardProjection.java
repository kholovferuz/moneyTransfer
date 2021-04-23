package uz.pdp.task.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task.entity.Card;

import java.sql.Timestamp;

@Projection(types = Card.class)
public interface CardProjection {
    Integer getId();

    String getUsername();

    long getCardNumber();

    Timestamp getExpirationDate();

    Double getBalance();
}
