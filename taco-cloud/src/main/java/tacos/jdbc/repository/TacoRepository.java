package tacos.jdbc.repository;

import tacos.jdbc.data.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
