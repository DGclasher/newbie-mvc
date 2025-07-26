package io.springguru.springrestmvc.services;

import io.springguru.springrestmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerCsvServiceImplTest {
    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws Exception {
        // Load the CSV file from classpath
        ClassPathResource resource = new ClassPathResource("csvdata/beers.csv");
        try (InputStream inputStream = resource.getInputStream()) {
            // Call the updated method
            List<BeerCSVRecord> recs = beerCsvService.convertCSV(inputStream);

            System.out.println("Records loaded: " + recs.size());
            assertThat(recs.size()).isGreaterThan(0);
        }
    }
}