package com.github.virginonline.planetsandlords.service;

import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;

import java.util.List;

public interface PlanetService {

    /**
     * Get all
     * @return {@link List} of {@link PlanetDto}
     */
    List<PlanetDto> getAll();

    /**
     *
     * @param id {@link Long} of planet
     * @return {@link PlanetDto}
     */
    PlanetDto getById(Long id) throws PlanetNotFoundException;

    /**
     * Add new Planet
     * @param planDto {@link PlanetDto}
     * @return planet
     */
    PlanetDto add(PlanetDto planDto);

    /**
     * Update Planet
     * @param id
     * @param planetDto {@link PlanetDto}
     * @return {@link PlanetDto}
     */
    PlanetDto update(Long id,PlanetDto planetDto) throws PlanetNotFoundException;

    /**
     * Delete planet by Id
     * @param id planet
     */
    void deleteById(Long id) throws PlanetNotFoundException;

}
