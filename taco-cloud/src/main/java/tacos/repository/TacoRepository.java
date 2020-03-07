package tacos.repository;

import tacos.repository.data.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
