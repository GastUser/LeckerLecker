package pizza.leckerlecker.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pizza.leckerlecker.entity.Lieferant;
import pizza.leckerlecker.entity.repository.LieferantRepository;

@RestController
public class Suchdateicontroller {

    @Autowired
    LieferantRepository lieferantRepository;

    @RequestMapping(value = "/searchdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<String> searchdata() {
        List<String> ret = new ArrayList<>();
        List<Lieferant> findAll = lieferantRepository.findAll();
        findAll.forEach((lieferant) -> {
            ret.add(lieferant.getPlz() + " " + lieferant.getOrt());
        });
        return ret;
    }
}