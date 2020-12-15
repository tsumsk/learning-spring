package spring.jdbc.template.example;

public interface SingerDao {
	String findNameById(Long id);

	String findNameByIdV2(Long id);
}
