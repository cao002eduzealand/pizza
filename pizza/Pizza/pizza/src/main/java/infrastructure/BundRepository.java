package infrastructure;

import domain.Bund;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BundRepository {

    private final JdbcTemplate jdbcTemplate;

    public BundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Bund gemBund(Bund bund) {
        String sql = "INSERT INTO bund (navn, pris) VALUES (?, ?)";

        jdbcTemplate.update(sql, bund.getNavn(), bund.getPris());
        return bund;
    }

    public Bund findBundById(int id) {
        String sql = "SELECT * FROM bund WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Bund.class), id);
    }

    public List<Bund> findAllBunds() {
        String sql = "SELECT * FROM bund";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bund.class));
    }

    public void deleteBund(int id) {
        String sql = "DELETE FROM bund WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
