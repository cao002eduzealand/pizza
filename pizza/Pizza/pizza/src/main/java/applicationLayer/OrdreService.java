package applicationLayer;

import domain.Bruger;
import domain.Ordre;
import domain.Pizza;
import infrastructure.OrdreRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdreService {

    private final OrdreRepository repository;

    public OrdreService(OrdreRepository repository) {
        this.repository = repository;
    }

    public Ordre gemOrdre(Ordre ordre, List<Pizza> cart) {
        return repository.gemOrdre(ordre, cart);
    }

    public List<Map<String, Object>> getOrdersForBruger(int brugerId) {
        return repository.findOrdersByBrugerId(brugerId);
    }

    public Ordre getOrdreById(int id) {
        return repository.findOrdreById(id);
    }

    public List<Ordre> getAllOrdrer() {
        return repository.findAllOrdrer();
    }

    public void deleteOrdre(int id) {
        repository.deleteOrdre(id);
    }



}
