package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "index_new";
    }

    @GetMapping("/listing")
    public String listing(@RequestParam(value = "plz_ort", required = false) String location, Model rucksack) {

        List<Lieferant> listeLieferanten = new ArrayList<>();
        
        
        
        System.out.println("Eingabe: " + location);
        if (null != location && !location.equals("")) {
            String[] split = location.split(" ");
            if (null != split && split.length > 1) {

                String plz = split[0];
                String ort = split[1];

                List<Lieferant> lieferanten = new ArrayList<>();
                if (null == ort) {
                    listeLieferanten = lieferantRepository.findByOrtIgnoreCaseContainingOrPlzIgnoreCaseContaining(plz, plz);
                } else {
                    listeLieferanten = lieferantRepository.findByOrtIgnoreCaseContainingAndPlzIgnoreCaseContaining(ort, plz);
                }

                rucksack.addAttribute("sucheingabe", location);

               
            }
        } else {
          listeLieferanten = lieferantRepository.findAll();

        }
rucksack.addAttribute("suchergebnisse", listeLieferanten);
        return "listing";
    }

}
