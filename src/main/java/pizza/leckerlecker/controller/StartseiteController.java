package pizza.leckerlecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author marco
 */
@Controller
public class StartseiteController {

    private final Logger log = LoggerFactory.getLogger(StartseiteController.class);

    

    /**
     * Das ist der Endpunkt für die <strong>Startseite</strong>
     *
     * @return Weiterleitung zum Startseiten HTML Template
     */
    @GetMapping("/")
    public String startseite() {
        log.debug("Startseite aufgerufen!");
        return "startseite";
    }

    
    
}
