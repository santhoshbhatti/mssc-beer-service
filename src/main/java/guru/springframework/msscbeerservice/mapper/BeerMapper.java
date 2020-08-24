package guru.springframework.msscbeerservice.mapper;

import guru.springframework.msscbeerservice.model.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    public Beer beerDtoToBeer(BeerDto beerDto);
    public BeerDto beerToBeerDto(Beer beer);
}
