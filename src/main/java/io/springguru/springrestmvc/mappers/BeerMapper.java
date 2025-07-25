package io.springguru.springrestmvc.mappers;

import io.springguru.springrestmvc.entities.Beer;
import io.springguru.springrestmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);
    BeerDTO beerToBeerDto(Beer beer);
}
