package belajar.mvc.jdbc.view;

import belajar.mvc.jdbc.controllers.CountryController;
import belajar.mvc.jdbc.controllers.RegionController;
import belajar.mvc.jdbc.models.Region;

import java.util.Scanner;

public class RegionView {

  private final RegionController regionController;
  private final Scanner scanner;

  public RegionView(RegionController regionController) {
    this.regionController = regionController;
    this.scanner = new Scanner(System.in);
  }

  public void showRegions(){
    regionController.getAllRegion();
  }

  public void insertRegion(){
    title("Insert Region");

    System.out.print("Region name: ");
    String name = scanner.nextLine();

    Region region = new Region(name);
    regionController.createRegion(region);
  }

  public void findById(){
    title("Find Region by ID");

    System.out.print("Region id: ");
    String input = scanner.nextLine();

    regionController.findById(input);
  }

  public void searchByName(){
    title("Search Region by Name");

    System.out.print("Region name: ");
    String name = scanner.nextLine();

    regionController.searchByName(name);
  }

  public void remove(){
    title("Remove Region");

    System.out.print("Region id: ");
    String input = scanner.nextLine();

    regionController.delete(input);
  }

  public void update(){
    title("Update Region");

    System.out.print("Region id: ");
    String id = scanner.nextLine();

    System.out.print("New region name: ");
    String name = scanner.nextLine();

    regionController.update(id, name);
  }

  public static void title(String title){
    System.out.println("\n\t\t==== " + title + " ====\n");
  }

  public static void header(String title){
    title(title);
    System.out.println("------------------------------------");
    System.out.println("| ID |\tNAME                       |");
    System.out.println("------------------------------------");
  }

  public static void content(Region region){
    System.out.printf("| %2d ", region.getId());
    System.out.printf("|\t%-25s  |", region.getName());
    System.out.print("\n");
  }

  public static void footer(){
    System.out.println("------------------------------------");
  }
}
