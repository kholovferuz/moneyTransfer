package uz.pdp.task.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OutputDTO {
    @NotNull(message = "Sending card should not be empty")
    private Integer fromCardId;

    @NotNull(message = "Receiving card should not be empty")
    private Integer toCardId;

    @NotNull(message = "Amount should not be empty")
    private Double amount;

}
