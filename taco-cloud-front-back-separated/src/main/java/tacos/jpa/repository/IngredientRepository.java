package tacos.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
