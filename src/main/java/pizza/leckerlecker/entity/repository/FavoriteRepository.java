/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.leckerlecker.entity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pizza.leckerlecker.entity.Favorite;

/**
 *
 * @author Chilliger
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserIdAndLieferantenId(String userId, Long lieferantenId);

    List<Favorite> findByUserId(String userId);

    List<Favorite> findByUserIdAndIsAktivTrue(String userId);
}
