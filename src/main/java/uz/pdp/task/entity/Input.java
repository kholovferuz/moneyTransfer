package uz.pdp.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card fromCard;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Card toCard;

    @Column(nullable = false)
    private Double amount;

    @CreationTimestamp
    private Timestamp date;
}
