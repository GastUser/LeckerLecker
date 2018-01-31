package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.List;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

/**
 *
 * @author marco
 */
@Controller
public class AdminLieferant {

    @Autowired
    LieferantRepository lieferantRepository;

    @GetMapping("/admin/lieferanten")
    public String AdminLieferant() {

        return "index";
    }
}

   