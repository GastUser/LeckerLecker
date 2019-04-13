package pizza.leckerlecker.entity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pizza.leckerlecker.entity.Lieferant;

/**
 *
 * @author Chilliger
 */
public interface LieferantRepository extends JpaRepository<Lieferant, Long> {

    List<Lieferant> findByOrtIgnoreCaseContainingOrPlzIgnoreCaseContaining(String suchfeldOrt, String suchfeldPlz);

    List<Lieferant> findByOrtIgnoreCaseContainingAndPlzIgnoreCaseContaining(String suchfeldOrt, String suchfeldPlz);

    List<Lieferant> findByOrtIgnoreCaseContaining(String suchort);

    //public Lieferant findOne(Long id);
}
