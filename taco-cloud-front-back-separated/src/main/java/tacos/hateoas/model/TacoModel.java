package tacos.hateoas.model;

import java.util.Date;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import tacos.hateoas.assembler.IngredientModelAssembler;
import tacos.jpa.data.Taco;

@Getter
@Relation(itemRelation = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {
    private static final IngredientModelAssembler ingredientModelAssembler = new IngredientModelAssembler();

    private final String name;

    private final Date createdAt;

    private final CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientModelAssembler.toCollectionModel(taco.getIngredients());
    }
}
