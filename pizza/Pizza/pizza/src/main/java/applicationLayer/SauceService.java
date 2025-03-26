package applicationLayer;

import domain.Sauce;
import infrastructure.SauceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SauceService {

    private final SauceRepository repository;

    public SauceService(SauceRepository repository) {
        this.repository = repository;
    }

    public Sauce gemSauce(Sauce sauce) {
        return repository.gemSauce(sauce);
    }

    public Sauce getSauceById(int id) {
        return repository.findSauceById(id);
    }

    public List<Sauce> getAllSauces() {
        return repository.findAllSauces();
    }

    public void deleteSauce(int id) {
        repository.deleteSauce(id);
    }
}
