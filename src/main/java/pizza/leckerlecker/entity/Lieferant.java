package pizza.leckerlecker.entity;

/**
 *
 * @author marco
 */
public class Lieferant {

    private Long id;
    private String name;
    private String telefon;
    private String webseite;
    private String speisekarte;

    public Lieferant() {
    }

    public Lieferant(String name, String telefon, String webseite, String speisekarte) {
        this.name = name;
        this.telefon = telefon;
        this.webseite = webseite;
        this.speisekarte = speisekarte;
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
