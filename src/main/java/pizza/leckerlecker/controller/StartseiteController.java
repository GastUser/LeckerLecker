package pizza.leckerlecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listing(@RequestParam(value = "plz_ort", required = false) String location, Model rucksack) {
        
        System.out.println("Eingabe: " + location);
        
        rucksack.addAttribute("sucheingabe", location);
        
        return "listing";
    }
    
}
