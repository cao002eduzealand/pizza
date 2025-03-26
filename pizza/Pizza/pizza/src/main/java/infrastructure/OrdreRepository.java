package infrastructure;

import domain.Ordre;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdreRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ordre gemOrdre(Ordre ordre) {
        String sql = "INSERT INTO ordre (bruger_id, dato, pris) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, ordre.getId(), ordre.getDato(), ordre.getPris());
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
    }

