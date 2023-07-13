package fer.infobip.project.CRUDtesting.controller;

import fer.infobip.project.CRUDtesting.model.Beer;
import fer.infobip.project.CRUDtesting.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BeerController {

    private final BeerService beerService;
    @PostMapping(value = "/saveOrUpdateBeer")
    public ResponseEntity<Void> saveOrUpdateBeer(@RequestBody Beer beer) {
        beerService.saveOrUpdateBeer(beer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllBeers")
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> allBeers = beerService.getAllBeers();
        return new ResponseEntity<>(allBeers, HttpStatus.OK);
    }

    @GetMapping(value = "/getBeer")
    public ResponseEntity<Beer> getBeerById(@RequestParam("beerId") String beerId) {
        Beer beer;
        try {
            beer = beerService.getBeerById(Integer.parseInt(beerId));
            return new ResponseEntity<>(beer, HttpStatus.OK);
        } catch (NumberFormatException e) {
            log.error("[getBeer] NumberFormatException for given id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping(value = "/delete/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(value = "beerId") String beerId) {
        try {
            beerService.deleteBeerById(Integer.parseInt(beerId));
        } catch (NumberFormatException e) {
            log.error("[deleteBeer] NumberFormatException for given id");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
