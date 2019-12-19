package io.diffblue.corebanking.ui.menu;

import io.diffblue.corebanking.compliance.CheckCompliance;
import io.diffblue.corebanking.CoreBanking;

public class ComplianceMenu extends Menu {
  private static final String MENU_TITLE = "Compliance Menu";
  private static final String[] MENU_MAIN =
      new String[] {"1. Run compliance check", "2. View last compliance results", "0. Back"};

  /**
   * Constructor.
   *
   * @param coreBanking CoreBanking instance.
   */
  public ComplianceMenu(CoreBanking coreBanking) {
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
        CheckCompliance.checkAccountCompliance(this.coreBanking.getAccounts());
        break;
      case 2:
        CheckCompliance.viewComplianceCheckResults();
        break;
      case 0:
        System.out.println();
        break;
      default:
        System.out.println("This should never happen... Invalid option: " + menuOpt);
        break;
    }
  }
}
