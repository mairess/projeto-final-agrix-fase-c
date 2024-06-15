package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * All crops list.
   *
   * @return the list
   */
  @GetMapping
  @Operation(summary = "Gets all crops", description = "Returns all crops available on system.")
  @ApiResponse(
      responseCode = "200",
      description = "All crops",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class))))
  public List<CropDto> allCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}")
  @Operation(summary = "Gets crop by id", description = "Returns a crop by its id.")
  @ApiResponse(
      responseCode = "200",
      description = "The crop",
      content = @Content(schema = @Schema(implementation = CropDto.class)))
  public CropDto getCropById(@PathVariable Long cropId) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(cropId)
    );
  }

  /**
   * Harvest date at crops list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  @GetMapping("/search")
  @Operation(summary = "Crops by harvestDate", description = "Returns crops by its harvestDate.")
  @ApiResponse(
      responseCode = "200",
      description = "All crops",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class))))
  public List<CropDto> harvestDateAtCrops(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> allCrops = cropService.findExpiresAtProducts(start, end);
    return allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Add crop fertilizer string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Associates crop fertilizer", description = "Makes the association")
  @ApiResponse(
      responseCode = "201",
      description = "Associated",
      content = @Content(schema = @Schema(type = "string")))
  public String addCropFertilizer(@PathVariable Long cropId, @PathVariable Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    return cropService.addFertilizer(cropId, fertilizerId);
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  @Operation(summary = "Fertilizers by crop", description = "Returns Fertilizers by its crop.")
  @ApiResponse(
      responseCode = "200",
      description = "All fertilizers",
      content = @Content(array = @ArraySchema(
          schema = @Schema(implementation = FertilizerDto.class)
      )))
  public List<Fertilizer> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    return cropService.findAllCropFertilizersById(cropId);
  }
}