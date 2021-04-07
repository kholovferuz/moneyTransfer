package uz.pdp.task.DTO;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class InputDTO {
    private Integer fromCardId;

    private Integer toCardId;

    private Double amount;

    private Timestamp date;

}
