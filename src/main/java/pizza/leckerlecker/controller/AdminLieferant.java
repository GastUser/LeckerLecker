package pizza.leckerlecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String AdminLieferant() {

        return "admin.lieferanten";
    }

    @PostMapping("/admin-lieferanten")
    public String speichereLieferant(
            @RequestParam(value = "lieferantenname", required = false) String lieferantenName,
            @RequestParam(value = "telefon", required = false) String telefon
    ) {
log.info("Lieferanten speichern: " + lieferantenName + "Phone: " + telefon);
        return "admin.lieferanten";
    }
}
