package fer.infobip.project.CRUDtesting.service;

import fer.infobip.project.CRUDtesting.dao.BeerDAO;
import fer.infobip.project.CRUDtesting.model.Beer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BeerService {

    private final BeerDAO beerDAO;
    public void saveOrUpdateBeer(Beer beer) {
        log.info("Saving beer object");
        beerDAO.save(beer);
    }

    public List<Beer> getAllBeers() {
        log.info("Getting all beer objects");
        return beerDAO.findAll();
    }

    public Beer getBeerById(int beerId) {
        log.info("Getting beer object by id {}", beerId);
        return beerDAO.findById(beerId).orElse(null);
    }

    public void deleteBeer(Beer beer) {
        log.info("Deleting beer object");
        beerDAO.delete(beer);
    }

    public void deleteBeerById(int beerId) {
        log.info("Deleting beer object with given id");
        beerDAO.deleteById(beerId);
    }

}
