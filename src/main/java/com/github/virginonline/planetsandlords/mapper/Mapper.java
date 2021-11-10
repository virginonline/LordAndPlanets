package com.github.virginonline.planetsandlords.mapper;

import com.github.virginonline.planetsandlords.dto.AbstractDto;
import com.github.virginonline.planetsandlords.entity.AbstractEntity;

public interface Mapper<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);
}
