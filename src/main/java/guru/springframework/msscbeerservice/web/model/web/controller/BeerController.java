package guru.springframework.msscbeerservice.web.model.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
        //TODO impl
        ResponseEntity<BeerDto> responseEntity = new ResponseEntity<BeerDto>(BeerDto.builder().build(),HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto){
        //TODO impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId,@RequestBody BeerDto beerDto){
        //TODO impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
