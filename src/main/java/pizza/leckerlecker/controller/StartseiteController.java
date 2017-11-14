package pizza.leckerlecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author marco
 */
@Controller
public class StartseiteController {
    
    @GetMapping("/")
    public String startseite() {
        return "index";
    }
    
    @GetMapping("/listing")
    public String listing() {
        
        return "listing";
    }
    
}
