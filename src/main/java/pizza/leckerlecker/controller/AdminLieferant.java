package pizza.leckerlecker.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private JavaMailSender sender;
    
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
//Email senden
try {
sendEmail(lieferant);
}catch(Exception ex){
    return "Error in sending email:" +ex;
    
}

return "redirect:/listing";

    }
@GetMapping("/loeschen")
public String loescheLieferant(
@RequestParam(value="lid", required = true) Long loeschId,
        RedirectAttributes redirectAttributes) {
log.info("Lösche Lieferant mit ID : " + loeschId);

Lieferant lieferantToDelete = lieferantRepository.findOne(loeschId);
String nachricht = "Löschen von " + lieferantToDelete.getName() + " erfolgreich!";        
lieferantRepository.delete(loeschId);


redirectAttributes.addFlashAttribute("meldung", nachricht);

return "redirect:/listing";



}

private void sendEmail(Lieferant lieferant) throws MessagingException {
MimeMessage message = sender.createMimeMessage();
MimeMessageHelper helper = new MimeMessageHelper(message);
helper.setTo("empfaenger@me.com");
helper.setFrom("absender@me.com");
helper.setText("Neuer Lieferant:" + lieferant.getName());
helper.setSubject("Neuer Kunde");
sender.send(message);

}


}
