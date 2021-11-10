package com.github.virginonline.planetsandlords.service;

import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.entity.Lord;
import com.github.virginonline.planetsandlords.entity.Planet;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;
import com.github.virginonline.planetsandlords.mapper.PlanetMapper;
import com.github.virginonline.planetsandlords.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService{
    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    @Override
    public List<PlanetDto> getAll() {
        return planetRepository.findAll().stream()
                .map(planetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanetDto getById(Long id) throws PlanetNotFoundException {
        return planetRepository.findById(id)
                .map(planetMapper::toDto)
                .orElseThrow(() -> new PlanetNotFoundException(id));
    }
    Planet getPlanetById(Long id) throws PlanetNotFoundException {
        return planetRepository.findById(id)
                .orElseThrow(() -> new PlanetNotFoundException(id));
    }
    Planet save(Planet planet) {
        return planetRepository.save(planet);
    }
    PlanetDto add(PlanetDto planetDto, Lord lord) {
        return planetMapper.toDto(
                planetRepository.save(new Planet(planetDto, lord))
        );
    }
    @Override
    public PlanetDto add(PlanetDto planetDto) {
        return add(planetDto, null);
    }

    @Override
    public PlanetDto update(Long id, PlanetDto planetDto) throws PlanetNotFoundException {
        Planet planet = getPlanetById(id);
        planet.update(planetDto);
        return planetMapper.toDto(planetRepository.save(planet));
    }

    @Override
    public void deleteById(Long id) throws PlanetNotFoundException {
        try {
            planetRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new PlanetNotFoundException(id);
        }
    }
}
