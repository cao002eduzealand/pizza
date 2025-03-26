package infrastructure;

import domain.Topping;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToppingRepository {

    private final JdbcTemplate jdbcTemplate;

    public ToppingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Topping gemTopping(Topping topping) {
        String sql = "INSERT INTO topping (navn, pris) VALUES (?, ?)";

        jdbcTemplate.update(sql, topping.getNavn(), topping.getPris());
        return topping;
    }

    public Topping findToppingById(int id) {
        String sql = "SELECT * FROM topping WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Topping.class), id);
    }

    public List<Topping> findAllToppings() {
        String sql = "SELECT * FROM topping";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Topping.class));
    }

    public void deleteTopping(int id) {
        String sql = "DELETE FROM topping WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
