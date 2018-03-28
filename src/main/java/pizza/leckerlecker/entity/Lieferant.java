package pizza.leckerlecker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author marco
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Lieferant(String name, String telefon, String webseite, String speisekarte, String ort, String plz) {
        this.name = name;
        this.telefon = telefon;
        this.webseite = webseite;
        this.speisekarte = speisekarte;
        this.ort = ort;
        this.plz = plz;
    }
}
