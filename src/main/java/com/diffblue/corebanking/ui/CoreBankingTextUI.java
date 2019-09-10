package com.diffblue.corebanking.ui;

import com.diffblue.corebanking.CoreBanking;
import com.diffblue.corebanking.datamanagement.ReadFromDB;
import com.diffblue.corebanking.transaction.TransactionException;
import com.diffblue.corebanking.ui.menu.MainMenu;
import com.diffblue.corebanking.ui.menu.Menu;

public class CoreBankingTextUI {
  private final CoreBanking coreBanking;

  /**
   * The constructor.
   *
   * @param coreBanking The CoreBanking instance to work with.
   */
  public CoreBankingTextUI(CoreBanking coreBanking) {
    this.coreBanking = coreBanking;
  }

  /**
   * The main method.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    try {
      // Create core banking instance
      CoreBankingTextUI textUI = new CoreBankingTextUI(ReadFromDB.readFromDB());
      textUI.startTextUI();

    } catch (TransactionException e) {
      e.printStackTrace();
    }
  }

  /** Starts the TextUI. */
  private void startTextUI() {
    Menu mainMenu = new MainMenu(coreBanking);
    mainMenu.startMenu();
  }
}
