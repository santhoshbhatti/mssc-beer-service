package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.model.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;
    public BeerLoader(BeerRepository beerRepository){
        this.beerRepository=beerRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }
    public void loadBeerObjects(){
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("UB")
                    .beerStyle("lager")
                    .price(new BigDecimal(100.0))
                    .minOnHand(35)
                    .quantityToBrew(68)
                    .upc(234L)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Kingfisher")
                    .beerStyle("lager")
                    .price(new BigDecimal(150.0))
                    .minOnHand(35)
                    .quantityToBrew(68)
                    .upc(235L)
                    .build());

        }
        System.out.println("#beers loaded >>>>>> "+beerRepository.count());
    }
}
