package pizza.leckerlecker.api;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

/**
 * API-Schnittstelle für Lieferantendaten-Abruf
 *
 * @author Chilliger
 */
@RestController
public class LieferantenApi {

    private final Logger log = LoggerFactory.getLogger(LieferantenApi.class);

    @Autowired
    LieferantRepository lieferantRepository;

    @GetMapping(value = "/api/lieferant")
    @CrossOrigin(origins = "*")
    public List<Lieferant> findAll() {
        return lieferantRepository.findAll();
    }
}
