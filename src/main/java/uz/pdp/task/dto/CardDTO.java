package uz.pdp.task.dto;

import lombok.*;


import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CardDTO {
    @NotNull(message = "Cardholder name should not be empty")
    private String cardholerName;

    @NotNull(message = "Card number should not be empty")
    private String cardNumber;

    @NotNull(message = "Balance should not be empty")
    private Double balance;


}
