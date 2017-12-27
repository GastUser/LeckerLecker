package pizza.leckerlecker.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

/**
 *
 * @author marco
 */
@Controller
public class StartseiteController {

    @Autowired
    LieferantRepository lieferantRepository;

    @GetMapping("/")
    public String startseite() {

        List<Lieferant> alleLieferanten = lieferantRepository.findAll();

        Lieferant neuerLieferant1 = new Lieferant("Hallo Pizza", "0800 123", "http://www.pizza.de", "http://www.pizza.de/menue", "Leipzig", "04275");
        Lieferant neuerLieferant2 = new Lieferant("Pizza King", "0800 654645", "http://www.pizzaking.de", "http://www.pizzaking.de/menue", "Gelsenkirchen", "45899");
        lieferantRepository.save(neuerLieferant1);
        lieferantRepository.save(neuerLieferant2);

        System.out.println("Anzahl :" + lieferantRepository.count());
        return "index";
    }

    @GetMapping("/listing")
    public String listing(@RequestParam(value = "plz_ort", required = false) String location, Model rucksack) {

        System.out.println("Eingabe: " + location);

        List<Lieferant> lieferanten = lieferantRepository.findByOrtIgnoreCaseContainingOrPlzIgnoreCaseContaining(location, location);
        rucksack.addAttribute("sucheingabe", location);

        rucksack.addAttribute("suchergebnisse", lieferanten);

        return "listing";
    }

}
