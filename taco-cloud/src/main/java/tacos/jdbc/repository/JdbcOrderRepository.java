package tacos.jdbc.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.jdbc.data.Order;
import tacos.jdbc.data.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;

    private SimpleJdbcInsert orderTacosInserter;

    private ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id");

        this.orderTacosInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setCreatedAt(new Date());
        long orderId = saveOrderInfo(order);
        saveOrderTacos(order.getTacos(), orderId);
        return order;
    }

    private long saveOrderInfo(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("createdAt", order.getCreatedAt());

        long orderId = orderInserter.executeAndReturnKey(values).longValue();

        return orderId;
    }

    private void saveOrderTacos(List<Taco> tacos, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrderId", orderId);

        for (Taco taco : tacos) {
            values.put("tacoId", taco.getId());
            orderTacosInserter.execute(values);
        }
    }
}
