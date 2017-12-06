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

        if (null == alleLieferanten || alleLieferanten.size() < 1) {
            Lieferant neuerLieferant1 = new Lieferant("Hallo Pizza", "0800 123", "http://www.pizza.de", "http://www.pizza.de/menue");
            lieferantRepository.save(neuerLieferant1);

        }
        System.out.println("Anzahl :" + lieferantRepository.count() );
        return "index";
    }

    @GetMapping("/listing")
    public String listing(@RequestParam(value = "plz_ort", required = false) String location, Model rucksack) {

        System.out.println("Eingabe: " + location);

        rucksack.addAttribute("sucheingabe", location);

        return "listing";
    }

}
