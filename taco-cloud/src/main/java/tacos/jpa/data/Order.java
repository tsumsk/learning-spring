package tacos.jpa.data;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import tacos.security.data.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip code is required")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name = "cc_cvv")
    private String ccCVV;

    private Date createdAt;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(name = "Taco_Order_Tacos",
        joinColumns = @JoinColumn(name = "taco_order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "taco_id", referencedColumnName = "id"))
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne(targetEntity = User.class)
    @JoinTable(name = "Taco_User_Orders",
        joinColumns = @JoinColumn(name = "taco_order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "taco_user_id", referencedColumnName = "id"))
    private User user;

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @PrePersist
    private void createdAt() {
        this.createdAt = new Date();
    }
}
