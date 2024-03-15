package belajar.mvc.jdbc.view;

import belajar.mvc.jdbc.controllers.CountryController;
import belajar.mvc.jdbc.controllers.RegionController;
import belajar.mvc.jdbc.daos.CountryDAO;
import belajar.mvc.jdbc.daos.RegionDAO;
import belajar.mvc.jdbc.tools.Utils;

import java.util.Scanner;

public class AppView {
  private final Scanner scanner;

  private final RegionView regionView;
  private final CountryView countryView;

  public AppView() {
    RegionDAO regionDAO = new RegionDAO();
    CountryDAO countryDAO = new CountryDAO();

    scanner = new Scanner(System.in);
    regionView = new RegionView(new RegionController(regionDAO));
    countryView = new CountryView(new CountryController(countryDAO), regionDAO);
  }

  public void mainMenu(){


    Scanner scanner = new Scanner(System.in);

    String[] listMenu = {
      "1. VIEW REGION",
      "2. VIEW COUNTRY"
    };

    boolean isNext = true;

    while (isNext) {

      header("MAIN MENU");

      for (String menu : listMenu) {
        System.out.println(menu);
      }

      System.out.print("\nselect : ");
      String operator = scanner.next();

      switch (operator){
        case "1":
          regionView();
          break;
        case "2":
          countryView();
          break;
        default:
          System.out.println("Pilih input yang benar " + Utils.getMenuLength(listMenu));
      }
      isNext = Utils.getYesOrNo("Tampilkan main menu");
    }
  }

  private void countryView() {
    boolean isNext = true;

    String[] listMenu = {
            "1. SHOW COUNTRY",
            "2. INSERT COUNTRY",
            "3. FIND COUNTRY BY ID",
            "4. SEARCH COUNTRY BY NAME",
            "5. REMOVE COUNTRY",
            "6. UPDATE COUNTRY",
    };


    while (isNext) {

      header("COUNTRY MENU");

      for (String menu : listMenu) {
        System.out.println(menu);
      }

      System.out.print("\nselect : ");
      String operator = scanner.next();

      switch (operator){
        case "1":
          countryView.showCountries();
          break;
        case "2":
          countryView.insertCountry();
          break;
        case "3":
          countryView.findById();
          break;
        case "4":
          countryView.searchByName();
          break;
        case "5":
          countryView.remove();
          break;
        case "6":
          countryView.update();
          break;
        default:
          System.out.println("Pilih input yang benar " + Utils.getMenuLength(listMenu));
      }

      isNext = Utils.getYesOrNo("Apakah anda ingin melanjutkan pada menu country");
    }

  }

  public void regionView(){


    boolean isNext = true;

    String[] listMenu = {
            "1. SHOW REGION",
            "2. INSERT REGION",
            "3. FIND REGION BY ID",
            "4. SEARCH REGION BY NAME",
            "5. REMOVE REGION",
            "6. UPDATE REGION",
    };


    while (isNext) {

      header("REGION MENU");

      for (String menu : listMenu) {
        System.out.println(menu);
      }

      System.out.print("\nselect : ");
      String operator = scanner.next();

      switch (operator){
        case "1":
          regionView.showRegions();
          break;
        case "2":
          regionView.insertRegion();
          break;
        case "3":
          regionView.findById();
          break;
        case "4":
          regionView.searchByName();
          break;
        case "5":
          regionView.remove();
          break;
        case "6":
          regionView.update();
          break;
        default:
          System.out.println("Pilih input yang benar " + Utils.getMenuLength(listMenu));
      }

      isNext = Utils.getYesOrNo("Apakah anda ingin melanjutkan pada menu region");
    }
  }

  public void header(String title){
    System.out.println("\n============================");
    System.out.printf("|\t\t%-17s  |", title);
    System.out.println("\n============================\n");
  }
}
