package io.springguru.springrestmvc.repositories;

import io.springguru.springrestmvc.bootstrap.BootstrapData;
import io.springguru.springrestmvc.entities.Beer;
import io.springguru.springrestmvc.model.BeerStyle;
import io.springguru.springrestmvc.services.BeerCsvService;
import io.springguru.springrestmvc.services.BeerCsvServiceImpl;
import io.springguru.springrestmvc.services.BeerServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }

    @Test
    void testListBeerByStyle() {
        List<Beer> list = beerRepository.findAllByBeerStyle(BeerStyle.IPA);
        assertThat(list.size()).isEqualTo(548);
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