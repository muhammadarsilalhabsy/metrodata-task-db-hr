package belajar.mvc.jdbc.controllers;

import belajar.mvc.jdbc.daos.CountryDAO;
import belajar.mvc.jdbc.daos.RegionDAO;
import belajar.mvc.jdbc.models.Country;
import belajar.mvc.jdbc.models.Region;
import belajar.mvc.jdbc.view.CountryView;

import java.util.List;
import java.util.Objects;

public class CountryController {

  private final CountryDAO countryDAO;

  public CountryController(CountryDAO countryDAO) {
    this.countryDAO = countryDAO;
  }

  public void getAllCountry() {
    List<Country> countries = countryDAO.getAllCountry();

    if (countries.size() > 0) {

      CountryView.header("COUNTRY LIST");
      for (Country country : countries) {
        CountryView.content(country);
      }
      CountryView.footer();
    } else {
      System.err.println("Country belum di buat!");
    }
  }

  public void create(Country country) {

    if (!countryDAO.searchName(country.getName()) && !Objects.nonNull(countryDAO.findById(country.getId()))) {

      if (country.getName().length() > 3) {
        countryDAO.create(country);
        System.out.println("Berhasil menambahkan country baru");
      } else {
        System.err.println("Panjang country name harus lebih dari 3 character!");
      }

    } else {
      System.err.println("Nama atau ID country yang di input sudah ada di database!");
    }
  }

  public void findById(String input) {

    Country country = countryDAO.findById(input);
    if (Objects.nonNull(country)) {
      CountryView.header("Search Result");
      CountryView.content(country);
      CountryView.footer();
    } else {
      System.err.println("Country tidak ditemukan!");
    }

  }

  public void searchByName(String name) {
    List<Country> countries = countryDAO.searchByName(name);

    if (countries.size() > 0) {
      CountryView.header("Search Result");
      for (Country country : countries) {
        CountryView.content(country);
      }
      CountryView.footer();
    } else {
      System.err.println("Region tidak ditemukan!");
    }
  }

  public void delete(String id) {

    if (Objects.nonNull(countryDAO.findById(id))) {
      countryDAO.delete(id);
      System.out.println("Berhasil menghapus country");
    } else {
      System.err.println("Country tidak ditemukan!");
    }
  }

  public void update(String id, String name, String regionId) {

    if(Objects.nonNull(countryDAO.findById(id))){
      if (!countryDAO.searchName(name)) {
        try {

          int parseRegionId = Integer.parseInt(regionId);
          RegionDAO regionDAO = new RegionDAO();
          Region regionCandidate = regionDAO.findById(parseRegionId);

          if (Objects.nonNull(regionCandidate)) {
            countryDAO.update(new Country(id, name, regionCandidate));
            System.out.println("Berhasil mengupdate country");
          } else {
            System.err.println("Region tidak ditemukan!");
          }

        } catch (NumberFormatException e) {
          System.err.println("Input region id harus berupa angka!");
        }
      } else {
        System.err.println("Nama country yang di input, sudah ada di database!");
      }
    }else{
      System.err.println("Country tidak ditemukan!");
    }

  }
}
