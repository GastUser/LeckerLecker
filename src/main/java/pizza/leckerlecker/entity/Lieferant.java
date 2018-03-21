package pizza.leckerlecker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marco
 */
@Entity
public class Lieferant {

    @Id
    @GeneratedValue

    

    private Long id;
    @NotNull
    @Size(min = 2, max = 80)
    private String name;
    private String telefon;
    private String webseite;
    private String speisekarte;
    private String ort;
    private String plz;

    public Lieferant() {
    }

    public Lieferant(String name, String telefon, String webseite, String speisekarte, String ort, String plz) {
        this.name = name;
        this.telefon = telefon;
        this.webseite = webseite;
        this.speisekarte = speisekarte;
        this.ort = ort;
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getWebseite() {
        return webseite;
    }

    public void setWebseite(String webseite) {
        this.webseite = webseite;
    }

    public String getSpeisekarte() {
        return speisekarte;
    }

    public void setSpeisekarte(String speisekarte) {
        this.speisekarte = speisekarte;
    }

}