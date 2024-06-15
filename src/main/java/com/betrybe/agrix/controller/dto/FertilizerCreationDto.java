package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Farm creation dto.
 */
public record FertilizerCreationDto(String name, String brand, String composition) {

  /**
   * To entity farmId.
   *
   * @return the farmId
   */
  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}