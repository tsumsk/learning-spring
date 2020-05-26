package tacos.jpa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.jpa.data.Ingredient;
import tacos.jpa.data.Ingredient.Type;
import tacos.jpa.repository.IngredientRepository;

@Slf4j
@RestController
@RequestMapping(path = "/ingredient", produces = "application/json")
public class JPAIngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> ingredientById(@PathVariable("id") String id) {
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<Ingredient>>> allIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(e -> ingredients.add(e));

        if (CollectionUtils.isEmpty(ingredients)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Type[] types = Type.values();
        Map<String, List<Ingredient>> response = new HashMap<>(types.length);
        for (Type type : types) {
            response.put(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
            .filter(e -> e.getType() == type)
            .collect(Collectors.toList());
    }
}
