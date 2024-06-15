package com.betrybe.agrix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {

  /**
   * The Planted date.
   */
  @Column(name = "planted_date")
  LocalDate plantedDate;
  /**
   * The Harvest date.
   */
  @Column(name = "harvest_date")
  LocalDate harvestDate;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double plantedArea;

  @ManyToMany
  @JoinTable(
      name = "crops_fertilizers",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<Fertilizer> fertilizers = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {
  }

  /**
   * Instantiates a new Crop.
   *
   * @param name        the name
   * @param plantedArea the planted area
   * @param plantedDate the planted date
   * @param harvestDate the harvest date
   */
  public Crop(String name, double plantedArea, LocalDate plantedDate, LocalDate harvestDate) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets planted area.
   *
   * @return the planted area
   */
  public double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Gets farmId.
   *
   * @return the farmId
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Sets farmId.
   *
   * @param farm the farmId
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  /**
   * Gets planted date.
   *
   * @return the planted date
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Gets harvest date.
   *
   * @return the harvest date
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}