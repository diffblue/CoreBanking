package io.diffblue.corebanking.ui.menu;

import io.diffblue.corebanking.CoreBanking;

public class MainMenu extends Menu {
  private static final String MENU_TITLE = "Main Menu";
  private static final String[] MENU_MAIN =
      new String[] {
        "1. Clients menu",
        "2. Accounts menu",
        "3. Compliance menu",
        "4. Core Banking data management menu",
        "0. Exit Core Banking"
      };

  /**
   * Constructor.
   *
   * @param coreBanking CoreBanking instance.
   */
  public MainMenu(CoreBanking coreBanking) {
    super(MENU_TITLE, MENU_MAIN, coreBanking);
  }

  /**
   * Executes the passed menu option.
   *
   * @param menuOpt The menu option to execute.
   */
  protected void executeMenuOption(int menuOpt) {
    switch (menuOpt) {
      case 1:
        ClientsMenu clientsMenu = new ClientsMenu(coreBanking);
        clientsMenu.startMenu();
        break;
      case 2:
        AccountsMenu accountsMenu = new AccountsMenu(coreBanking);
        accountsMenu.startMenu();
        break;
      case 3:
        ComplianceMenu complianceMenu = new ComplianceMenu(coreBanking);
        complianceMenu.startMenu();
        break;
      case 4:
        CoreBankingDataManagementMenu coreBankingDataManagementMenu =
            new CoreBankingDataManagementMenu(coreBanking);
        coreBankingDataManagementMenu.startMenu();
        break;
      case 0:
        System.out.println("Bye bye.");
        break;
      default:
        System.out.println("This should never happen... Invalid option: " + menuOpt);
        break;
    }
  }
}
