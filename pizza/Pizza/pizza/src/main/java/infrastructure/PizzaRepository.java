package infrastructure;

import domain.Pizza;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Repository
public class PizzaRepository {

    private final JdbcTemplate jdbcTemplate;


    public PizzaRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Pizza gemPizza(Pizza pizza){
        String sql = "Insert into pizza (navn, beskrivelse, sauce_id, bund_id, pris, image_url) VALUES (?, ?, ?, ?, ? ,?, ?)";


        jdbcTemplate.update(sql, pizza.getNavn(), pizza.getBeskrivelse(), pizza.getSauce(), pizza.getBund(), pizza.getPris(), pizza.getImageUrl());
        return pizza;
    }

    public Pizza findPizzaById(int id){
        String sql = "select * from pizza where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Pizza.class), id);
    }

    public List<Pizza> findAllPizzas(){
        String sql = "select * from pizza";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pizza.class));
    }


    public void deletePizza(int id){
        String sql = "DELETE FROM pizza WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
