package infrastructure;

import domain.Sauce;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SauceRepository {

    private final JdbcTemplate jdbcTemplate;

    public SauceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Sauce gemSauce(Sauce sauce) {
        String sql = "INSERT INTO sauce (navn, pris) VALUES (?, ?)";

        jdbcTemplate.update(sql, sauce.getNavn(), sauce.getPris());
        return sauce;
    }

    public Sauce findSauceById(int id) {
        String sql = "SELECT * FROM sauce WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Sauce.class), id);
    }

    public List<Sauce> findAllSauces() {
        String sql = "SELECT * FROM sauce";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sauce.class));
    }

    public void deleteSauce(int id) {
        String sql = "DELETE FROM sauce WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
