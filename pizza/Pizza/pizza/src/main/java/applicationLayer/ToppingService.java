package applicationLayer;

import domain.Topping;
import infrastructure.ToppingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

    private final ToppingRepository repository;

    public ToppingService(ToppingRepository repository) {
        this.repository = repository;
    }

    public Topping gemTopping(Topping topping) {
        return repository.gemTopping(topping);
    }

    public Topping getToppingById(int id) {
        return repository.findToppingById(id);
    }

    public List<Topping> getAllToppings() {
        return repository.findAllToppings();
    }

    public void deleteTopping(int id) {
        repository.deleteTopping(id);
    }
}
