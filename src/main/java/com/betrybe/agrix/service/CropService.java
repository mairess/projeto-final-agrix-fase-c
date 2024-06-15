package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Find by id crop.
   *
   * @param cropId the crop id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long cropId) throws CropNotFoundException {
    return cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find expires at products list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> findExpiresAtProducts(LocalDate start, LocalDate end) {

    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Add fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public String addFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Find all crop fertilizers by id list.
   *
   * @param cropId the crop id
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Fertilizer> findAllCropFertilizersById(Long cropId)
      throws CropNotFoundException {
    Crop crop = findById(cropId);
    return crop.getFertilizers();
  }

}