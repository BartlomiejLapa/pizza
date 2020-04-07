package pl.bartek.pizza.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.bartek.pizza.Ingredient;
import pl.bartek.pizza.Ingredient.Type;
import pl.bartek.pizza.Pizza;
import pl.bartek.pizza.data.IngredientRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPizzaController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("PSZE", "pszenna", Type.CIASTO),
                new Ingredient("KUKU", "kukurydziana", Type.CIASTO),
                new Ingredient("WOLO", "wołowina", Type.MIESO),
                new Ingredient("WIEP", "wieprzowina", Type.MIESO),
                new Ingredient("SALA", "salami", Type.MIESO),
                new Ingredient("CIEN", "cieńkie", Type.GRUBOSC),
                new Ingredient("GRUB", "grube", Type.GRUBOSC),
                new Ingredient("SERP", "pojedynczy", Type.SER),
                new Ingredient("SERD", "podwójny", Type.SER)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

/*
    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Pizza());
        return "design";
    }
*/

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        model.addAttribute("design", new Pizza());
        return "design";
    }
    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Pizza design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Przetwarzanie zamówienia: " + design);
        return "redirect:/orders/current";

    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
