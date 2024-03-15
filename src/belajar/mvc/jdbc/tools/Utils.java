package belajar.mvc.jdbc.tools;

import java.util.Scanner;

public class Utils {
  public static boolean getYesOrNo(String pesan) {
    Scanner input = new Scanner(System.in);
    System.out.print("\n" + pesan + " (y/n)? ");
    String userIn = input.next();

    // check jika userIn sama dengan 'y' atau 'n'
    // kalau bukan 'y' atau bukan 'n' maka minta input secara terus menerus
    // sampai dengan usernIn adalah 'y' atau 'n'
    while (!userIn.equalsIgnoreCase("y") &&
            !userIn.equalsIgnoreCase("n")) {
      System.err.println("Pilihan kamu bukan y atau n");
      System.out.print("\n" + pesan + " (y/n)? ");
      userIn = input.next();
    }

    return userIn.equalsIgnoreCase("y");
  }

  public static String getMenuLength(String[] menuList){
    final int base = 1;
    int last = menuList.length;
    return  "("+ base + "-"+ last +")";
  }
}
