package applicationLayer;

import domain.Pizza;
import infrastructure.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository repository;


    public PizzaService(PizzaRepository repository) {
        this.repository = repository;
    }


    public Pizza gemPizza (Pizza pizza){
        return repository.gemPizza(pizza);
    }
    public Pizza getPizzaById(int id){
        return repository.findPizzaById(id);
    }

    public List<Pizza> getAllPizzas(){
        return repository.findAllPizzas();

    }



    public void deletePizza(int id){
        repository.deletePizza(id);
    }
}
