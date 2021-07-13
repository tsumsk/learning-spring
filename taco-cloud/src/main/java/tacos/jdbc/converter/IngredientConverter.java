package tacos.jdbc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.jdbc.data.Ingredient;
import tacos.jdbc.repository.IngredientRepository;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
