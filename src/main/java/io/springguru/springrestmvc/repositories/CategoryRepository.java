package io.springguru.springrestmvc.repositories;

import io.springguru.springrestmvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    // Additional query methods can be defined here if needed
}
