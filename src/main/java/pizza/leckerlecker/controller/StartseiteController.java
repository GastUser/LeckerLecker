package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.List;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
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
        if (null != location && !location.equals("")) {
            String[] split = location.split(" ");
            if (null != split && split.length > 1) {

                String plz = split[0];
                String ort = split[1];

                List<Lieferant> lieferanten = new ArrayList<>();
                if (null == ort) {
                    lieferanten = lieferanten = lieferantRepository.findByOrtIgnoreCaseContainingOrPlzIgnoreCaseContaining(plz, plz);
                } else {
                    lieferanten = lieferantRepository.findByOrtIgnoreCaseContainingAndPlzIgnoreCaseContaining(ort, plz);
                }

                rucksack.addAttribute("sucheingabe", location);

                rucksack.addAttribute("suchergebnisse", lieferanten);
            }
        } else {
            rucksack.addAttribute("suchergebnisse", lieferantRepository.findAll());

        }

        return "listing";
    }

}
