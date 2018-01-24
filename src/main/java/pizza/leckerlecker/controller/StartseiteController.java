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

     return "index";   
    }

    @GetMapping("/listing")
    public String listing(@RequestParam(value = "plz_ort", required = false) String location, Model rucksack) {

        System.out.println("Eingabe: " + location);

        String[] split = location.split(" ");
        if (null != split && split.length > 1) {

            String plz = split[0];
            String ort = split[1];

            List<Lieferant> lieferanten = lieferantRepository.findByOrtIgnoreCaseContainingOrPlzIgnoreCaseContaining(ort, plz);
            rucksack.addAttribute("sucheingabe", location);

            rucksack.addAttribute("suchergebnisse", lieferanten);
        }

        return "listing";
    }

}
