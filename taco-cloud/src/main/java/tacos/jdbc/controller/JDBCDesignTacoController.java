package tacos.jdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.jdbc.data.Ingredient;
import tacos.jdbc.data.Ingredient.Type;
import tacos.jdbc.data.Order;
import tacos.jdbc.data.Taco;
import tacos.jdbc.repository.IngredientRepository;
import tacos.jdbc.repository.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/jdbc/design")
@SessionAttributes("order")
public class JDBCDesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    @Autowired
    public JDBCDesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute
    private Order order() {
        return new Order();
    }

    @ModelAttribute
    private Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    private void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(e -> ingredients.add(e));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing design: " + taco);
        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);

        return "redirect:/jdbc/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(e -> e.getType() == type)
                .collect(Collectors.toList());
    }
}
