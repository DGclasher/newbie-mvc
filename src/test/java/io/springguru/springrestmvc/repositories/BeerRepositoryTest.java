package io.springguru.springrestmvc.repositories;

import io.springguru.springrestmvc.bootstrap.BootstrapData;
import io.springguru.springrestmvc.entities.Beer;
import io.springguru.springrestmvc.model.BeerStyle;
import io.springguru.springrestmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeerByName() {
        Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);
        assertThat(list.getContent().size()).isEqualTo(336);
    }

    @Test
    void testListBeerByStyle() {
        Page<Beer> list = beerRepository.findAllByBeerStyle(BeerStyle.IPA, null);
        assertThat(list.getContent().size()).isEqualTo(548);
    }

    @Test
    void testSaveBeerTooLongName() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer skdshfksd ksdfhksdf skdfhsdf skfhksdf skdfsdjfhsdk fsdkhf skdf")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("43453453")
                    .price(new BigDecimal("11.21"))
                    .build());
            beerRepository.flush();
        });
    }


    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("43453453")
                        .price(new BigDecimal("11.21"))
                .build());
        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}