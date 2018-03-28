package pizza.leckerlecker.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

/**
 *
 * @author marco
 */
@Controller
public class AdminLieferant {

    private final Logger log = LoggerFactory.getLogger(AdminLieferant.class);
    @Autowired
    LieferantRepository lieferantRepository;

    @GetMapping("/admin-lieferanten")
    public String AdminLieferant(Model model) {
        Lieferant lieferant = new Lieferant();
        lieferant.setPlz("04275");
        lieferant.setOrt("lieferant");

        model.addAttribute("lieferant", lieferant);

        return "admin.lieferanten";
    }

    @PostMapping("/admin-lieferanten")
    public String speichereLieferant(@Valid Lieferant lieferant, BindingResult result,Model model) {
        log.info("Lieferanten speichern: " + lieferant.getName() + "Phone :" + lieferant.getTelefon());
        
        if(result.hasErrors()) {
            log.info("Fehler vorhanden");

            int errorCount = result.getErrorCount();
            String error = result.getAllErrors().get(0).toString();
            log.info("Anzahl Fehler: " + errorCount);
            log.info("Fehler: " + error);
 return "admin.lieferanten";
        }
lieferantRepository.save(lieferant);
        return "redirect:/listing";

    }

}
