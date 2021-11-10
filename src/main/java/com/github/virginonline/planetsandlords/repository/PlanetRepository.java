package com.github.virginonline.planetsandlords.repository;

import com.github.virginonline.planetsandlords.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface repository for {@link Planet} class
 */
@Repository
public interface PlanetRepository extends JpaRepository<Planet,Long> {
}
