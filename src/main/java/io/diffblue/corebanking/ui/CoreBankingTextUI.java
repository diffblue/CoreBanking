/*
    Copyright 2018-2024 Diffblue Limited

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package io.diffblue.corebanking.ui;

import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import io.diffblue.corebanking.ui.menu.MainMenu;
import io.diffblue.corebanking.ui.menu.Menu;

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
