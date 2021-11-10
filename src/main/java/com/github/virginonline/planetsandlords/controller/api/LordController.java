package com.github.virginonline.planetsandlords.controller.api;

import com.github.virginonline.planetsandlords.dto.LordDto;
import com.github.virginonline.planetsandlords.dto.PlanetDto;
import com.github.virginonline.planetsandlords.exception.ExceptionResponse;
import com.github.virginonline.planetsandlords.exception.LordNotFoundException;
import com.github.virginonline.planetsandlords.exception.PlanetNotFoundException;
import com.github.virginonline.planetsandlords.service.LordServiceImpl;
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
@RequestMapping(value = "api/v1/lords", produces = MediaType.APPLICATION_JSON_VALUE)
public class LordController {

    private final LordServiceImpl lordServiceImpl;

    public LordController(LordServiceImpl lordServiceImpl) {
        this.lordServiceImpl = lordServiceImpl;
    }


    @Operation(summary = "Get all Lords")
    @ApiResponse(responseCode = "200", description = "Get all lords",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LordDto.class)))})
    @ApiResponse(responseCode = "404", description = "Invalid Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @GetMapping()
    public ResponseEntity<Object> getAll()
            throws LordNotFoundException {
        List<LordDto> lords = lordServiceImpl.getAll();
        if (lords.isEmpty()) {
            throw new LordNotFoundException();
        }
        return ResponseEntity.ok(lords);
    }
    @Operation(summary = "Get loafer Lords")
    @ApiResponse(responseCode = "200", description = "Get loafer Lords",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LordDto.class)))})
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @GetMapping("/loafers")
    public ResponseEntity<Object> getLoafers()
            throws LordNotFoundException {
        List<LordDto> lords = lordServiceImpl.getLoafers();
        if (lords.isEmpty()) {
            throw new LordNotFoundException();
        }
        return ResponseEntity.ok(lords);
    }
    @Operation(summary = "Get Lord by id")
    @ApiResponse(responseCode = "200", description = "Get by id Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LordDto.class))})
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @Min(0) @PathVariable Long id
    ) throws LordNotFoundException {
        LordDto lord = lordServiceImpl.getById(id);
        return ResponseEntity.ok(lord);
    }
    @Operation(summary = "Get top 10 Lords by age")
    @ApiResponse(responseCode = "200", description = "Get top 10 Lords",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LordDto.class)))})
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @GetMapping("/top")
    public ResponseEntity<Object> getTop()
            throws LordNotFoundException {
        List<LordDto> lords = lordServiceImpl.getTopTen();
        if (lords.isEmpty()) {
            throw new LordNotFoundException();
        }
        return ResponseEntity.ok(lords);
    }
    @Operation(summary = "Create new Lord")
    @ApiResponse(responseCode = "201", description = "Create new Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LordDto.class))})
    @ApiResponse(responseCode = "400", description = "Invalid Lord supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PostMapping()
    public ResponseEntity<Object> post(
            @Valid @RequestBody LordDto lordDto
    ) {
        LordDto put = lordServiceImpl.add(lordDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(put);
    }
    @Operation(summary = "Create new Planet assigned to Lord")
    @ApiResponse(responseCode = "201", description = "Create new Planet assigned to Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LordDto.class))})
    @ApiResponse(responseCode = "400", description = "Invalid Planet supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addPlanet(
            @Min(0) @PathVariable("id") Long id,
            @Valid @RequestBody PlanetDto planetDto
    ) throws LordNotFoundException {
        PlanetDto planet = lordServiceImpl.addPlanet(id, planetDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(planet);
    }
    @Operation(summary = "Assign Planet to the Lord")
    @ApiResponse(responseCode = "200", description = "Assign Planet to the Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LordDto.class))})
    @ApiResponse(responseCode = "404", description = "Not found Lord or Planet",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PutMapping("/{id}/assign_planet")
    public ResponseEntity<Object> assignPlanetToLord(
            @Min(0) @PathVariable("id") Long lordId,
            @Min(0) @RequestParam("planet_id") Long planetId
    ) throws LordNotFoundException, PlanetNotFoundException {
        PlanetDto update = lordServiceImpl.assignToManagePlanet(lordId, planetId);
        return ResponseEntity.ok(update);
    }
    @Operation(summary = "Update Lord")
    @ApiResponse(responseCode = "200", description = "Update Lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LordDto.class))})
    @ApiResponse(responseCode = "400", description = "Invalid Lord supplied",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @Min(0) @PathVariable("id") Long id,
            @Valid @RequestBody LordDto lordDto
    ) throws LordNotFoundException {
        LordDto update = lordServiceImpl.update(id, lordDto);
        return ResponseEntity.ok(update);
    }
    @Operation(summary = "Delete Lord by id")
    @ApiResponse(responseCode = "204", description = "Delete Lord by id")
    @ApiResponse(responseCode = "404", description = "Not found lord",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(
            @Min(0) @PathVariable Long id
    ) throws LordNotFoundException {
        lordServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("{\"status\": 204}");
    }
}
