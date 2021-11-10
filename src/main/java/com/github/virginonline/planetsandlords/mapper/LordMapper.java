package com.github.virginonline.planetsandlords.mapper;

import com.github.virginonline.planetsandlords.dto.LordDto;
import com.github.virginonline.planetsandlords.entity.Lord;
import org.springframework.stereotype.Component;

@Component
public class LordMapper extends AbstractMapper<Lord, LordDto>{
    public LordMapper() {
        super(Lord.class, LordDto.class);
    }
}

