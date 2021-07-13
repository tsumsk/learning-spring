package tacos.jpa.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class Ingredient {
    @Id
    private final String id;

    private final String name;

    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    private Ingredient() {
        this.id = null;
        this.name = null;
        this.type = null;
    }
}
