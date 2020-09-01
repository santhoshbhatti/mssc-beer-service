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
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
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
                    .upc(BEER_1_UPC)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Kingfisher")
                    .beerStyle("lager")
                    .price(new BigDecimal(150.0))
                    .minOnHand(35)
                    .quantityToBrew(68)
                    .upc(BEER_2_UPC)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Budwieser")
                    .beerStyle("lager")
                    .price(new BigDecimal(10.0))
                    .minOnHand(45)
                    .quantityToBrew(90)
                    .upc(BEER_3_UPC)
                    .build());

        }
        System.out.println("#beers loaded >>>>>> "+beerRepository.count());
    }
}
