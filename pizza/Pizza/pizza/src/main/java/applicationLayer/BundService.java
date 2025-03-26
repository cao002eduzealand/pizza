package applicationLayer;

import domain.Bund;
import infrastructure.BundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundService {

    private final BundRepository repository;

    public BundService(BundRepository repository) {
        this.repository = repository;
    }

    public Bund gemBund(Bund bund) {
        return repository.gemBund(bund);
    }

    public Bund getBundById(int id) {
        return repository.findBundById(id);
    }

    public List<Bund> getAllBunds() {
        return repository.findAllBunds();
    }

    public void deleteBund(int id) {
        repository.deleteBund(id);
    }
}
