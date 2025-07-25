package io.springguru.springrestmvc.services;

import io.springguru.springrestmvc.model.BeerDTO;
import io.springguru.springrestmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);
    Optional<BeerDTO> getBeerById(UUID id);
    BeerDTO saveNewBeer(BeerDTO beer);
    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);
    Boolean deleteById(UUID beerId);
    void patchById(UUID beerId, BeerDTO beer);
}
