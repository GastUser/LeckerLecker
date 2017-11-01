/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.leckerlecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Chilliger
 */
@Controller
public class StartseiteController {
 
    @GetMapping("/")
    
    public String startseite(){
    return "startseite";
    
    
    
    }
            
}
