package pizza.leckerlecker.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

/**
 *
 * @author marco
 */
@Controller
public class IframeController {

    private final Logger log = LoggerFactory.getLogger(IframeController.class);

    @Autowired
    LieferantRepository lieferantRepository;

    /**
     * Das ist der Endpunkt für die <strong>iFrame Integration</strong>
     *
     * @return Weiterleitung zum iframe HTML Template
     */
    @GetMapping("/iframe")
    public String iframe(Model model) {
        List<Lieferant> alleLieferanten = lieferantRepository.findAll();
        model.addAttribute("lieferanten", alleLieferanten);

        return "iframe";
    }
}
