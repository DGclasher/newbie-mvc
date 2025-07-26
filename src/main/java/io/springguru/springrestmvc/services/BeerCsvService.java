package io.springguru.springrestmvc.services;

import io.springguru.springrestmvc.model.BeerCSVRecord;

import java.io.InputStream;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(InputStream csvInputStream);
}
