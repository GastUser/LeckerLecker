package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

        return "startseite";
    }

    @GetMapping("/listing")
    public String listing(
            @RequestParam(value = "plz_ort", required = false) String location, 
            @RequestParam(value = "kategorie", required = false) String kategorie,
            Model rucksack) {

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

                if(null!=kategorie) {
                    List<Lieferant> temp = new ArrayList<>();
                    
                    List<String> listeDerKategorien = Arrays.asList(kategorie.split("\\s*,\\s*"));
                    
                    for (Lieferant lieferant : listeLieferanten) {
                        for (String kat : listeDerKategorien) {
                            if(lieferant.getKategorie().contains(kat)) {
                                temp.add(lieferant);
                                break;
                            }
                        }
                        
                    }
                    listeLieferanten = temp;
                    
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
