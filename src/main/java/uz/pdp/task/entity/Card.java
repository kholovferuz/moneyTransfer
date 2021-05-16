package uz.pdp.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String cardholderName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private Double balance;

    private boolean active = true;


}
