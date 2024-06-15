package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Gets farmId by id.
   *
   * @param farmId the farmId id
   * @return the farmId by id
   * @throws FarmNotFoundException the farmId not found exception
   */
  @GetMapping("/{farmId}")
  @Operation(summary = "Gets farm by id", description = "Returns a farm by its id.")
  @ApiResponse(
      responseCode = "200",
      description = "The farm",
      content = @Content(schema = @Schema(implementation = FarmDto.class)))
  public FarmDto getFarmById(@PathVariable Long farmId) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(farmId)
    );
  }

  /**
   * Create farmId dto.
   *
   * @param farmCreationDto the farmId creation dto
   * @return the farmId dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Creates farm", description = "Cretes new farm")
  @ApiResponse(
      responseCode = "201",
      description = "Created farm",
      content = @Content(schema = @Schema(implementation = FarmDto.class)))
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * All farms list.
   *
   * @return the list
   */
  @GetMapping
  @Operation(summary = "Gets all farms", description = "Returns all farms available on system.")
  @ApiResponse(
      responseCode = "200",
      description = "All farms",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = FarmDto.class))))
  public List<FarmDto> allFarms() {
    List<Farm> allFarms = farmService.findAll();
    return allFarms.stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Create farmId crop crop dto.
   *
   * @param farmId          the farmId id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farmId not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Creates farm crop", description = "Cretes new crop to specified farm id")
  @ApiResponse(
      responseCode = "201",
      description = "Created crop",
      content = @Content(schema = @Schema(implementation = CropDto.class)))
  public CropDto createFarmCrop(@
      PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto)
      throws FarmNotFoundException {
    return CropDto.fromEntity(
        farmService.createFarmCrop(farmId, cropCreationDto.toEntity())
    );
  }

  /**
   * Create farm crop list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  @Operation(summary = "Gets crops by farm id", description = "Returns all crops by its farm id.")
  @ApiResponse(
      responseCode = "200",
      description = "The farms",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class))))
  public List<CropDto> getFarmCrops(@PathVariable Long farmId)
      throws FarmNotFoundException {
    List<Crop> crops = farmService.getFarmCrops(farmId);

    return crops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

}