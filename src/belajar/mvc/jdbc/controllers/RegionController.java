package belajar.mvc.jdbc.controllers;

import belajar.mvc.jdbc.daos.RegionDAO;
import belajar.mvc.jdbc.models.Region;
import belajar.mvc.jdbc.view.RegionView;

import java.util.List;
import java.util.Objects;

public class RegionController {

  private final RegionDAO regionDAO;

  public RegionController(RegionDAO regionDAO) {
    this.regionDAO = regionDAO;
  }

  public void getAllRegion(){
    List<Region> regions = regionDAO.getAll();

    if(regions.size() > 0){
      RegionView.header("REGION LIST");
      for (Region region : regions) {
        RegionView.content(region);
      }
      RegionView.footer();
    }else {
      System.err.println("Region belum di buat!");
    }
  }

  public void createRegion(Region region) {

    // check apakah nama region sudah ada di database atau belum
    if(!regionDAO.searchName(region.getName())){

      if(region.getName().length() > 3) {
        regionDAO.create(region);
        System.out.println("Berhasil menambahkan region baru");
      } else{
        System.err.println("Panjang region name harus lebih dari 3 character!");
      }

    }else{
      System.err.println("Nama region yang di input sudah ada di database!");
    }
  }

  public void findById(String input) {
    try {
      int id = Integer.parseInt(input);
      Region region = regionDAO.findById(id);

      if (Objects.nonNull(region)) {
        RegionView.header("Search Result");
        RegionView.content(region);
        RegionView.footer();
      } else {
        System.err.println("Region tidak ditemukan!");
      }
    } catch (NumberFormatException e) {
      System.err.println("Input harus berupa angka!");
    }
  }

  public void searchByName(String name) {
    List<Region> regions = regionDAO.searchByName(name);

    if (regions.size() > 0) {
      RegionView.header("Search Result");
      for (Region region : regions) {
        RegionView.content(region);
      }
      RegionView.footer();
    } else {
      System.err.println("Region tidak ditemukan!");
    }
  }

  public void delete(String input){

    try {
      int id = Integer.parseInt(input);
      Region region = regionDAO.findById(id);

      if (Objects.nonNull(region)) {
        regionDAO.delete(id);
        System.out.println("Berhasil menghapus region");
      } else {
        System.err.println("Region tidak ditemukan!");
      }
    } catch (NumberFormatException e) {
      System.err.println("Input harus berupa angka!");
    }
  }

  public void update(String id, String name){

    if(!regionDAO.searchName(name)){
      if(name.length() > 3){

        try{
          int regionId = Integer.parseInt(id);
          Region regionCandidate = regionDAO.findById(regionId);

          if (Objects.nonNull(regionCandidate)) {
            Region region = new Region(regionId, name);
            regionDAO.update(region);

            System.out.println("Berhasil mengupdate region");
          } else {
            System.err.println("Region tidak ditemukan!");
          }
        } catch (NumberFormatException e) {
          System.err.println("Input id harus berupa angka!");
        }

      }else{
        System.err.println("Panjang region name harus lebih dari 3 character!\nSilahkan mengulang proses dari awal lagi!");
      }
    }else{
      System.err.println("Nama region yang di input, sudah ada di database!");
    }

  }
}
