package pizza.leckerlecker.controller;

import java.io.IOException;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author marco
 */
@Controller
public class AdminLieferantController {

    private int maxUploadSize = 30000;
    private final Logger log = LoggerFactory.getLogger(AdminLieferantController.class);

    @Autowired
    private JavaMailSender sender;

    @Autowired
    LieferantRepository lieferantRepository;

    @Value("${app.kategorien}")
    private String[] kategorien;

    private List<String> gewaehlteKategorien = new ArrayList<>();

    @GetMapping("/favorite")
    public String addFavorite(){
    return"redirect:/listing";
    }
    
    @GetMapping("/admin-lieferanten")
    public String AdminLieferant(Model model) {
        Lieferant lieferant = new Lieferant();
        lieferant.setPlz("04275");
        lieferant.setOrt("lieferant");

        model.addAttribute("lieferant", lieferant);
        model.addAttribute("selectedKat", this.gewaehlteKategorien);
        model.addAttribute("kategorien", Arrays.asList(kategorien));

        return "admin.lieferanten";
    }

    @PostMapping("/admin-lieferanten")
    public String speichereLieferant(
            @Valid Lieferant lieferant,
            BindingResult result,
            @RequestParam("logo") MultipartFile logoUpload,
            Model model,
            RedirectAttributes redirectAttributes) {

        log.debug("Lieferanten speichern: " + lieferant.getName() + "Phone :" + lieferant.getTelefon());
        UrlValidator uv = new UrlValidator();
        log.debug("Webseiten-URL korrekt: " + uv.isValid(lieferant.getWebseite()));

        model.addAttribute("kategorien", Arrays.asList(this.kategorien));
        model.addAttribute("selectedKat", this.getSelectedKatAsList(lieferant.getKategorie()));

        if (!uv.isValid(lieferant.getWebseite())) {
            result.rejectValue("webseite", "", "Prüfen sie ihre Url - Ungültig!");
        }

        //Logo speichern
        if (!logoUpload.isEmpty()) {
            String contentType = logoUpload.getContentType();
            log.info("Content-Type: " + logoUpload.getContentType());
            log.info("Dateigroesse: " + logoUpload.getSize());

            if (logoUpload.getSize() > maxUploadSize) {
                log.info("zu groß");
                result.rejectValue("logoFile", "", "Logo ist zu gross max " + maxUploadSize / 1024 + "KB; dein Upload ist:" + logoUpload.getSize() / 1024 + "KB");
            } 
            else {
                log.info("passt");
            }

            if (contentType.equals("image/jpeg")
                    || contentType.equals("image/png")
                    || contentType.equals("image/jpg")) {
            } 
            else {
                log.info("Falscher Dateityp");
                result.rejectValue("logoFile", "", "Nur Bildformate zulässig(jpeg...)");
            }

            try {
                //String storeFile = fileService.storeFile(logoUpload);
                lieferant.setLogoPath(logoUpload.getName());
                lieferant.setLogoFile(Base64.encodeBase64String(logoUpload.getBytes()));
            } 
            catch (IOException ex) {
                log.error("Fehler beim Bild speichern");
            }
        } else {
            if (lieferant.getId() != null) {
                Lieferant alterStandLieferantAusDB = lieferantRepository.getOne(lieferant.getId());
                if (alterStandLieferantAusDB != null) {
                    lieferant.setLogoFile(alterStandLieferantAusDB.getLogoFile());
                }
            }
        }

        if (result.hasErrors()) {
            int errorCount = result.getErrorCount();
            String error = result.getAllErrors().get(0).toString();
            log.debug("Anzahl Fehler: " + errorCount);
            log.debug("Fehler: " + error);
            return "admin.lieferanten";
        }
        Lieferant l = lieferantRepository.save(lieferant);
        //Email senden
        try {
            //sendEmail(lieferant);
        } 
        catch (Exception ex) {
            return "Error in sending email:" + ex;
        }

        String nachricht = "Speichern von : " + l.getName() + " erfolgreich!";
        redirectAttributes.addFlashAttribute("meldung", nachricht);
        return "redirect:/listing";
    }

    @GetMapping("/bearbeiten")
    public String bearbeiteLieferant(
            @RequestParam(value = "bid", required = true) Long bearbeiteId,
            Model model
    ) {
        Lieferant bearbeiteLieferant = lieferantRepository.getOne(bearbeiteId);

        model.addAttribute("selectedKat", this.getSelectedKatAsList(bearbeiteLieferant.getKategorie()));
        model.addAttribute("lieferant", bearbeiteLieferant);
        model.addAttribute("kategorien", Arrays.asList(this.kategorien));

        return "admin.lieferanten";

    }

    @GetMapping("/loeschen")
    public String loescheLieferant(
            @RequestParam(value = "lid", required = true) Long loeschId,
            RedirectAttributes redirectAttributes) {
        log.debug("Lösche Lieferant mit ID : " + loeschId);

        Lieferant lieferantToDelete = lieferantRepository.getOne(loeschId);
        String nachricht = "Löschen von " + lieferantToDelete.getName() + " erfolgreich!";
        lieferantRepository.deleteById(loeschId);
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
     *
     * @param kommagetrennteKategorien
     * @return
     */
    private List<String> getSelectedKatAsList(String kommagetrennteKategorien) {
        List<String> ret = new ArrayList<>();

        if (null != kommagetrennteKategorien) {
            List<String> asList = Arrays.asList(kommagetrennteKategorien.split("\\s*,\\s*"));
            if (null != asList && asList.size() > 0) {
                ret = asList;
            }
        }
        return ret;
    }
}
