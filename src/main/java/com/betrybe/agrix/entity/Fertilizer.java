package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Fertilizer.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;

  private String composition;

  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops = new ArrayList<>();

  /**
   * Instantiates a new Fertilizer.
   */
  public Fertilizer() {

  }

  /**
   * Instantiates a new Fertilizer.
   *
   * @param name        the name
   * @param brand       the brand
   * @param composition the composition
   */
  public Fertilizer(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
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
   * Gets brand.
   *
   * @return the brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Gets composition.
   *
   * @return the composition
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Gets crops.
   *
   * @return the crops
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Sets crops.
   *
   * @param crops the crops
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}