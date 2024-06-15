package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farmId repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Find by id farmId.
   *
   * @param farmId the farmId id
   * @return the farmId
   * @throws FarmNotFoundException the farmId not found exception
   */
  public Farm findById(Long farmId) throws FarmNotFoundException {
    return farmRepository.findById(farmId)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Create farmId.
   *
   * @param farm the farmId
   * @return the farmId
   */
  public Farm create(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Create crop crop.
   *
   * @param farmId the farmId id
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFoundException the farmId not found exception
   */
  public Crop createFarmCrop(Long farmId, Crop crop) throws FarmNotFoundException {
    Farm farm = findById(farmId);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Gets farm crops.
   *
   * @param farmId the farm id
   * @return the farm crops
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> getFarmCrops(Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);

    return farm.getCrops();
  }

}