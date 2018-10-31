package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

@RestController
public class SuchdateiController {

    private final Logger log = LoggerFactory.getLogger(SuchdateiController.class);

    @Autowired
    LieferantRepository lieferantRepository;

    @RequestMapping(value = "/searchdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<String> searchdata() {
        log.debug("Methode sesrchdata" );
        List<String> ret = new ArrayList<>();
        List<Lieferant> findAll = lieferantRepository.findAll();

        findAll.forEach((lieferant) -> {
            String plzOrtKombination = lieferant.getPlz() + " " + lieferant.getOrt();
            if (!ret.contains(plzOrtKombination)) {
                ret.add(plzOrtKombination);
            }
        });
        return ret;
    }
}
