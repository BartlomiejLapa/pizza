package pl.bartek.pizza;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
public class Order {
    @NotBlank(message = "Pole obowiązkowe")
    private String name;
    @NotBlank(message = "Pole obowiązkowe")

    private String street;
    @NotBlank(message = "Pole obowiązkowe")

    @NotBlank(message = "Pole obowiązkowe")
    private String city;

    @NotBlank(message = "Pole obowiązkowe")
    private String zip;

    @CreditCardNumber(message = "Nieprawidłowy nr karty")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "Wartość musi mieć format MM/RR.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0,message = "Błędny kod CVV")
    private String ccCVV;

    private Long id;
    private Date placeAt;
}

