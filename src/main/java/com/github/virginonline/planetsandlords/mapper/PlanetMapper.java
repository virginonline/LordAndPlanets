package com.github.virginonline.planetsandlords.mapper;

import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.entity.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper extends AbstractMapper<Planet, PlanetDto>{
    public PlanetMapper() {
        super(Planet.class, PlanetDto.class);
    }
}
