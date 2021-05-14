package uz.pdp.task.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class CardDTO {
    @NotNull(message = "Username should not be empty")
    private String username;

    @NotNull(message = "Card number should not be empty")
    private long cardNumber;

    @NotNull(message = "Expiry date should not be empty")
    private Timestamp expirationDate;

    @NotNull(message = "Balance should not be empty")
    private Double balance;


}
