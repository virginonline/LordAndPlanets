package com.github.virginonline.planetsandlords.service;

import com.github.virginonline.planetsandlords.dto.LordDto;
import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.exception.LordNotFoundException;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;

import java.util.List;

public interface LordService {

    /**
     *
     * @param id the id ({@link Long}) of Lord
     * @return the {@link LordDto}
     */
    LordDto getById(Long id) throws LordNotFoundException;

    List<LordDto> getAll();

    List<LordDto> getLoafers();

    List<LordDto> getTopTen();

    /**
     *Add Lord
     * @param lordDto the {@link LordDto}
     * @return the {@link LordDto}
     */
    LordDto add(LordDto lordDto);

    /**
     * Add Planet
     * @param id the id  {@link Long} Lord
     * @param planetDto the {@link PlanetDto}
     * @return the {@link PlanetDto}
     */
    PlanetDto addPlanet(Long id, PlanetDto planetDto) throws LordNotFoundException;

    /**
     * Update lord.
     *
     * @param id      the id ({@link Long}) of Lord
     * @param lordDto the lord dto
     * @return the lord
     */
    LordDto update(Long id, LordDto lordDto);

    PlanetDto assignToManagePlanet(Long lordId, Long planetId)
            throws LordNotFoundException, PlanetNotFoundException;

    /**
     * Delete {@link LordDto} by id
     * @param id the id {@link LordDto}
     */
    void deleteById(Long id) throws LordNotFoundException;
}
