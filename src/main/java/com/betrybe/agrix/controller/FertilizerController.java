package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Gets fertilizer by id.
   *
   * @param fertilizerId the fertilizer id
   * @return the fertilizer by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{fertilizerId}")
  @Operation(summary = "Gets fertilizer by id", description = "Returns a fertilizer by its id.")
  @ApiResponse(
      responseCode = "200",
      description = "The fertilizer",
      content = @Content(schema = @Schema(implementation = FertilizerDto.class)))
  public FertilizerDto getFertilizerById(@PathVariable Long fertilizerId)
      throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(
        fertilizerService.findById(fertilizerId)
    );
  }

  /**
   * All fertilizers list.
   *
   * @return the list
   */
  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @Operation(summary = "Gets all fertilizers", description = "Returns all available fertilizers.")
  @ApiResponse(
      responseCode = "200",
      description = "All fertilizers",
      content = @Content(array = @ArraySchema(
          schema = @Schema(implementation = FertilizerDto.class
          ))))
  public List<FertilizerDto> allFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.allFertilizers();
    return allFertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }

  /**
   * Create fertilizer fertilizer dto.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Creates fertilizer", description = "Cretes new fertilizer")
  @ApiResponse(
      responseCode = "201",
      description = "Created fertilizer",
      content = @Content(schema = @Schema(implementation = FertilizerDto.class)))
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.fromEntity(
        fertilizerService.create(fertilizerCreationDto.toEntity())
    );
  }
}