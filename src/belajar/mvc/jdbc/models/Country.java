package belajar.mvc.jdbc.models;

public class Country {

  private String id;

  private String name;

  private Region region;

  public Country() {
  }

  public Country(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Country(String id, String name, Region region) {
    this.id = id;
    this.name = name;
    this.region = region;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }
}
