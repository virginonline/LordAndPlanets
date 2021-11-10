package com.github.virginonline.planetsandlords.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LordDto extends AbstractDto{
    @NotNull
    @Min(1)
    @Max(200)
    private Integer age;

    @Schema(hidden = true)
    @Null
    @JsonIgnoreProperties({"lord"})
    private List<PlanetDto> planets;

    public LordDto(String name, Integer age) {
        super(null, name);
        this.age = age;
        this.planets = null;
    }

    public LordDto(Long id, String name, Integer age) {
        super(id, name);
        this.age = age;
        this.planets = Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LordDto lord = (LordDto) o;
        return Objects.equals(age, lord.age)
                && Objects.equals(planets, lord.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, planets);
    }
}
