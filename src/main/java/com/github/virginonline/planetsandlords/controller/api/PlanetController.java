package com.github.virginonline.planetsandlords.controller.api;

import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.exception.ExceptionResponse;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;
import com.github.virginonline.planetsandlords.service.PlanetServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "api/v1/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetController {
    private final PlanetServiceImpl planetServiceImpl;

    public PlanetController(PlanetServiceImpl planetServiceImpl) {
        this.planetServiceImpl = planetServiceImpl;
    }

    @Operation(summary = "Get all Planets")
    @ApiResponse(responseCode = "200", description = "Get all planets",
    content = {@Content(mediaType = "application/json",
    array = @ArraySchema(schema = @Schema(implementation = PlanetDto.class)))})
    @ApiResponse(responseCode = "404", description = "Invalid Planet",
    content = {@Content(mediaType = "application/json",
    schema = @Schema(implementation = ExceptionResponse.class))})

    @GetMapping()
    public ResponseEntity<Object> getAll() throws PlanetNotFoundException{
        List<PlanetDto> planets = planetServiceImpl.getAll();

        if(planets.isEmpty()){
            throw new PlanetNotFoundException();
        }
        return ResponseEntity.ok(planets);
    }

    @Operation(summary = "Get Planet by Id")
    @ApiResponse(responseCode = "200", description = "Get Planet by Id",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlanetDto.class))})
    @ApiResponse(responseCode = "404", description = "Invalid Planet supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @Min(0) @PathVariable Long id
    ) throws PlanetNotFoundException {
        PlanetDto planet = planetServiceImpl.getById(id);
        return ResponseEntity.ok(planet);
    }



    @Operation(summary = "Create new Planet")
    @ApiResponse(responseCode = "201", description = "Create new Planet",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlanetDto.class))})
    @ApiResponse(responseCode = "400", description = "Invalid Planet supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PostMapping()
    public ResponseEntity<Object> add(
            @Valid @RequestBody PlanetDto planetDto
    ) {
        PlanetDto add = planetServiceImpl.add(planetDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(add);
    }


    @Operation(summary = "Update Planet")
    @ApiResponse(responseCode = "201", description = "Update Planet",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlanetDto.class))})
    @ApiResponse(responseCode = "400", description = "Invalid Planet supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @ApiResponse(responseCode = "404", description = "Not found Planet",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @Min(0) @PathVariable("id") Long planetId,
            @Valid @RequestBody PlanetDto planetDto
    ) throws PlanetNotFoundException {
        PlanetDto update = planetServiceImpl.update(planetId, planetDto);
        return ResponseEntity.ok(update);
    }



    @Operation(summary = "Delete Planet by id")
    @ApiResponse(responseCode = "204", description = "Delete Planet by id",
            content = @Content(schema = @Schema(allowableValues = "{\"status\": 204}")))
    @ApiResponse(responseCode = "404", description = "Not found planet",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(
            @Min(0) @PathVariable Long id
    ) throws PlanetNotFoundException {
        planetServiceImpl.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("{\"status\": 204}");
    }
}

