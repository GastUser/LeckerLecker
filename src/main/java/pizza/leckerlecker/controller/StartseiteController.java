package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(StartseiteController.class);

    @Autowired
    LieferantRepository lieferantRepository;

    /**
     * Das ist der Endpunkt für die <strong>Startseite</strong>
     * 
     * @return Weiterleitung zum Startseiten HTML Template
     */
    @GetMapping("/")
    public String startseite() {
        return "startseite";
    }

    /**
     * 
     * @param location
     * @param kategorie
     * @param model
     * @return 
     */
    @GetMapping("/listing")
    public String listing(
            @RequestParam(value = "plz_ort", required = false) String location,
            @RequestParam(value = "kategorie", required = false) String kategorie,
            Model model) {

        List<Lieferant> listeLieferanten = new ArrayList<>();

        log.debug("Eingabe: " + location);
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

                if (null != kategorie) {
                    List<Lieferant> temp = new ArrayList<>();
                    List<String> listeDerKategorien = Arrays.asList(kategorie.split("\\s*,\\s*"));

                    for (Lieferant lieferant : listeLieferanten) {
                        for (String kat : listeDerKategorien) {
                            if (lieferant.getKategorie().contains(kat)) {
                                temp.add(lieferant);
                                break;
                            }
                        }
                    }
                    listeLieferanten = temp;
                }

                model.addAttribute("sucheingabe", location);
            }
        } else {
            listeLieferanten = lieferantRepository.findAll();
        }
        model.addAttribute("suchergebnisse", listeLieferanten);
        return "listing";
    }
}
