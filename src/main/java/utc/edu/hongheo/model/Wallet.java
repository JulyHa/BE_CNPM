package utc.edu.hongheo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon;

    private String name;

    private double moneyAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int status;

    @ManyToOne
    @JoinColumn(name = "moneyType_id")
    private MoneyType moneyType;

}
