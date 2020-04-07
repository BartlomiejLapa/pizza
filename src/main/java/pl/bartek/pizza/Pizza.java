package pl.bartek.pizza;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Data
public class Pizza {
    @NotNull
    @Size(min = 4, message = "Nazwa musi mieć min 4 znaki")
    private String name;
    @Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik")
    private List<String> ingredients;
    private Long id;
    private Date placeAt;
}
