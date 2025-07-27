package io.springguru.springrestmvc.repositories;

import io.springguru.springrestmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
