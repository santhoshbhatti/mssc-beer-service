package guru.springframework.msscbeerservice.web.model.web.service;

import guru.springframework.msscbeerservice.mapper.BeerMapper;
import guru.springframework.msscbeerservice.model.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.web.controller.BeerService;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.UUID;
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper mapper;
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return mapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer beer=beerRepository.save(mapper.beerDtoToBeer(beerDto));
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer=beerRepository.findById(beerId).orElseThrow(NoSuchElementException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.beerToBeerDto(beerRepository.save(beer));
    }
}
