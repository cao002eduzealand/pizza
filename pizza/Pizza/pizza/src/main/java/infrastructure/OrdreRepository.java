package infrastructure;

import domain.Ordre;
import domain.Pizza;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class OrdreRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Ordre gemOrdre(Ordre ordre, List<Pizza> cart) {
        // SQL query to insert the order
        String sqlOrdre = "INSERT INTO ordre (bruger_id, dato, pris) VALUES (?, ?, ?)";

        // Insert the order and get the generated ID
        jdbcTemplate.update(sqlOrdre, ordre.getBruger().getId(), java.sql.Date.valueOf(ordre.getDato()), ordre.getPris());

        // Query the last inserted ID
        String sqlGetLastInsertId = "SELECT LAST_INSERT_ID()";
        int ordreId = jdbcTemplate.queryForObject(sqlGetLastInsertId, Integer.class);

        // Insert each pizza into ordre_pizza table
        String sqlOrdrePizza = "INSERT INTO ordre_pizza (ordre_id, pizza_id, antal) VALUES (?, ?, ?)";
        for (Pizza pizza : cart) {
            jdbcTemplate.update(sqlOrdrePizza, ordreId, pizza.getId(), 1); // Assuming 1 of each pizza
        }

        ordre.setId(ordreId); // Set the generated order ID
        return ordre;
    }




    public Ordre findOrdreById(int id) {
        String sql = "SELECT * FROM ordre WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ordre.class), id);
    }

    public List<Ordre> findAllOrdrer() {
        String sql = "SELECT * FROM ordre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ordre.class));
    }

        public void deleteOrdre(int id){
            String sql = "DELETE FROM ordre WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }



    public List<Map<String, Object>> findOrdersByBrugerId(int brugerId) {
        String sql = """
        SELECT o.id AS order_id, o.dato, o.pris, p.navn AS pizza_name
        FROM ordre o
        JOIN ordre_pizza op ON o.id = op.ordre_id
        JOIN pizza p ON op.pizza_id = p.id
        WHERE o.bruger_id = ?
        """;

        return jdbcTemplate.queryForList(sql, brugerId);
    }
    }

