package distributed.transaction.example;

import distributed.transaction.example.entities.Singer;

public interface SingerService {
	Singer save(Singer singer);
}
