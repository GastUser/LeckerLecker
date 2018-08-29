package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
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

   @Value("${app.kategorien}")
    private String[] kategorien;
    
   @GetMapping("/admin-lieferanten")
    public String AdminLieferant(Model model) {
        Lieferant lieferant = new Lieferant();
        lieferant.setPlz("04275");
        lieferant.setOrt("lieferant");

        model.addAttribute("lieferant", lieferant);
  model.addAttribute("selectedKat", this.gewaehlteKategorien);
        model.addAttribute("kategorien",Arrays.asList(kategorien));
        return "admin.lieferanten";
    }

    @PostMapping("/admin-lieferanten")
    public String speichereLieferant(
            @Valid 
            Lieferant lieferant,
            BindingResult result,
            Model model,
        RedirectAttributes redirectAttributes){
       
        
        
        
        log.info("Lieferanten speichern: " + lieferant.getName() + "Phone :" + lieferant.getTelefon());

        UrlValidator uv = new UrlValidator();
        log.info("Webseiten-URL korrekt: " + uv.isValid(lieferant.getWebseite()));
        
        model.addAttribute("selectedKat", this.getSelectedKatAsList(lieferant.getKategorie()));
        
       if( !uv.isValid(lieferant.getWebseite()) ) {
       result.rejectValue("webseite","", "Prüfen sie ihre Url - Ungültig!");
       
       } 
        
        
        if (result.hasErrors()) {
            log.info("Fehler vorhanden");

            int errorCount = result.getErrorCount();
            String error = result.getAllErrors().get(0).toString();
            log.info("Anzahl Fehler: " + errorCount);
            log.info("Fehler: " + error);
            return "admin.lieferanten";
        }
        Lieferant l = lieferantRepository.save(lieferant);
//Email senden
        try {
            sendEmail(lieferant);
        } catch (Exception ex) {
            return "Error in sending email:" + ex;

        }

        String nachricht = "Speichern von : " + l.getName() + " erfolgreich!";
        redirectAttributes.addFlashAttribute("meldung", nachricht);
        return "redirect:/listing";

    }

    @GetMapping("/bearbeiten")
    public String bearbeiteLieferant(
            @RequestParam(value = "bid", required = true) Long bearbeiteId,
            Model rucksack
    ) {
        Lieferant bearbeiteLieferant = lieferantRepository.findOne(bearbeiteId);
        rucksack.addAttribute("lieferant", bearbeiteLieferant);
       rucksack.addAttribute("kategorien", kategorien);
        return "admin.lieferanten";

    }

    @GetMapping("/loeschen")
    public String loescheLieferant(
            @RequestParam(value = "lid", required = true) Long loeschId,
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
/**
     * Helper String zu Liste
     * @param in
     * @return 
     */
    private List<String> getSelectedKatAsList(String kommagetrennteKategorien) {
        List<String> ret = new ArrayList<>();
       
        if(null!=kommagetrennteKategorien) {
            List<String> asList = Arrays.asList(kommagetrennteKategorien.split("\\s*,\\s*"));
            if(null != asList && asList.size()>0) {
                ret = asList;
            }
        }
        return ret;
    }
}
