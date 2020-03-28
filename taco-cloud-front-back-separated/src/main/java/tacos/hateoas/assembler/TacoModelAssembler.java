package tacos.hateoas.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.hateoas.model.TacoModel;
import tacos.jpa.controller.JPADesignTacoController;
import tacos.jpa.data.Taco;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

    public TacoModelAssembler() {
        super(JPADesignTacoController.class, TacoModel.class);
    }

    @Override
    public TacoModel instantiateModel(Taco taco) {
        return new TacoModel(taco);
    }

    @Override
    public TacoModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
