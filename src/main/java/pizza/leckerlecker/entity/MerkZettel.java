
package pizza.leckerlecker.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Chilliger
 */
public class MerkZettel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String userId;
    private Boolean isAktiv;
    }
