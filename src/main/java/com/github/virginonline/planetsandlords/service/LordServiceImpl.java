package com.github.virginonline.planetsandlords.service;

import com.github.virginonline.planetsandlords.dto.LordDto;
import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.entity.Lord;
import com.github.virginonline.planetsandlords.entity.Planet;
import com.github.virginonline.planetsandlords.exception.LordNotFoundException;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;
import com.github.virginonline.planetsandlords.mapper.LordMapper;
import com.github.virginonline.planetsandlords.mapper.PlanetMapper;
import com.github.virginonline.planetsandlords.repository.LordRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LordServiceImpl implements LordService{
    private final LordRepository lordRepository;
    private final PlanetServiceImpl planetService;
    private final LordMapper lordMapper;
    private final PlanetMapper planetMapper;

    @Override
    public LordDto getById(Long id) throws LordNotFoundException {
        return lordMapper.toDto(getLordById(id));
    }

    public Lord getLordById(Long id) throws LordNotFoundException {
        return lordRepository.findById(id)
                .orElseThrow(() -> new LordNotFoundException(id));
    }

    @Override
    public List<LordDto> getAll() {
        return lordRepository.findAll().stream()
                .map(lordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LordDto> getLoafers() {
        return lordRepository.findAllLoafers().stream()
                .map(lordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LordDto> getTopTen() {
        return lordRepository.findTop10ByOrderByAge().stream()
                .map(lordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LordDto add(LordDto lordDto) {
        return lordMapper.toDto(lordRepository.save(new Lord(lordDto)));
    }

    @Override
    public PlanetDto addPlanet(Long id, PlanetDto planetDto)
            throws LordNotFoundException {
        Lord lord = getLordById(id);
        return planetService.add(planetDto, lord);
    }

    @Override
    public LordDto update(Long id, LordDto lordDto) {
        return null;
    }

    @Override
    public PlanetDto assignToManagePlanet(Long lordId, Long planetId) throws LordNotFoundException, PlanetNotFoundException {
        Lord lord = getLordById(lordId);
        Planet planet = planetService.getPlanetById(planetId);
        planet.setLord(lord);
        return planetMapper.toDto(planetService.save(planet));
    }

    @Override
    public void deleteById(Long id) throws LordNotFoundException {
        try {
            lordRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new LordNotFoundException(id);
        }
    }
}
