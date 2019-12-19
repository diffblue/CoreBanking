package io.diffblue.corebanking.ui.menu;

import io.diffblue.corebanking.CoreBanking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Menu {
  protected final CoreBanking coreBanking;
  private final String MENU_TITLE;
  private final String[] MENU_ITEMS;

  /**
   * Constructor.
   *
   * @param menuItems The menu items.
   */
  protected Menu(String menuTitle, String[] menuItems, CoreBanking coreBanking) {
    this.MENU_TITLE = menuTitle;
    this.MENU_ITEMS = menuItems;
    this.coreBanking = coreBanking;
  }

  /** Prints the passed menu. */
  private void printMenu() {
    System.out.println("#### " + this.MENU_TITLE + " ####" + "\n");
    for (String s : MENU_ITEMS) {
      System.out.println(s);
    }
    System.out.println();
  }

  /**
   * Executes the passed menu option.
   *
   * @param menuOpt The menu option to execute.
   */
  protected abstract void executeMenuOption(int menuOpt);

  /**
   * Reads a menu option.
   *
   * @return A valid menu option.
   */
  private int readMenuOption() {
    int menuOpt = -1;

    do {
      System.out.println("Please select a menu option: ");

      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menuOpt = Integer.parseInt(reader.readLine());
      } catch (Exception e) {
        menuOpt = -1;
      }

      // Make sure the user selects an available option
      if (menuOpt < 0 || menuOpt >= MENU_ITEMS.length) {
        System.out.println("Invalid option.");
        menuOpt = -1;
      }

    } while (menuOpt < 0);

    return menuOpt;
  }

  /** Starts the execution of the menu. */
  public void startMenu() {
    int menuOpt = -1;
    do {
      this.printMenu();
      menuOpt = this.readMenuOption();
      this.executeMenuOption(menuOpt);
    } while (menuOpt != 0);
  }
}
