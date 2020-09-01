package guru.springframework.msscbeerservice.web.model.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
        //TODO impl
        BeerDto beerDto= beerService.getBeerById(beerId);
        ResponseEntity<BeerDto> responseEntity = new ResponseEntity<BeerDto>(beerDto,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        //TODO impl
        BeerDto beerDtoSaved=beerService.saveBeer(beerDto);
        return new ResponseEntity(beerDtoSaved,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto){
        //TODO impl
        BeerDto updatedBeerDto = beerService.updateBeer(beerId,beerDto);
        return new ResponseEntity(updatedBeerDto,HttpStatus.NO_CONTENT);
    }

}
