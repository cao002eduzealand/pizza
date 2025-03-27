package applicationLayer;

import domain.Bruger;
import infrastructure.BrugerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrugerService {


    private final BrugerRepository repository;

    public BrugerService(BrugerRepository repository) {
        this.repository = repository;
    }

    public Bruger authenticateUser(String email, String password) {
        return repository.authenticateUser(email, password);
    }

    public int gemBruger(Bruger bruger) {
        if (repository.emailExists(bruger.getEmail())) {
            throw new IllegalArgumentException("Email already exists! Please use another email.");
        }

        repository.gemBruger(bruger); // Save user if email is unique
        return repository.findIdByEmail(bruger.getEmail()); // Fetch correct ID
    }



    public Bruger getBrugerById(int id) {
        return repository.findBrugerById(id);
    }

    public List<Bruger> getAllBrugere() {
        return repository.findAllBrugere();
    }

    public void deleteBruger(int id) {
        repository.deleteBruger(id);
    }
}
