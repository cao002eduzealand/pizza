package infrastructure;

import domain.Bruger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrugerRepository {

    private final JdbcTemplate jdbcTemplate;

    public BrugerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Bruger gemBruger(Bruger bruger) {
        String sql = "INSERT INTO bruger (navn, password, adresse, email, bonuspoint) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, bruger.getNavn(), bruger.getPassword(), bruger.getAdresse(), bruger.getEmail(), bruger.getBonuspoint());
        return bruger;
    }
    public int findIdByEmail(String email) {
        String sql = "SELECT id FROM bruger WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    public Bruger findBrugerById(int id) {
        String sql = "SELECT * FROM bruger WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Bruger.class), id);
    }

    public List<Bruger> findAllBrugere() {
        String sql = "SELECT * FROM bruger";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bruger.class));
    }

    public void deleteBruger(int id) {
        String sql = "DELETE FROM bruger WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM bruger WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0; // Returns true if email exists
    }

    public Bruger authenticateUser(String email, String password) {
        String sql = "SELECT * FROM bruger WHERE email = ? AND password = ?";

        try {
            // Query the database, check if a match exists, and return the user
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> {
                Bruger bruger = new Bruger();
                bruger.setId(rs.getInt("id"));
                bruger.setEmail(rs.getString("email"));
                bruger.setPassword(rs.getString("password"));
                bruger.setNavn(rs.getString("navn"));
                bruger.setAdresse(rs.getString("adresse"));
                return bruger;
            });
        } catch (Exception e) {
            return null; // If no match found or error occurs, return null
        }
    }
}
