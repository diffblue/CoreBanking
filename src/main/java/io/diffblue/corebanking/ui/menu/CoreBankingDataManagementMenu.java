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

package io.diffblue.corebanking.ui.menu;

import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.datamanagement.WriteToDB;
import io.diffblue.corebanking.transaction.TransactionException;
import io.diffblue.corebanking.CoreBanking;

public class CoreBankingDataManagementMenu extends Menu {
  private static final String MENU_TITLE = "CoreBanking Data Management Menu";
  private static final String[] MENU_MAIN =
      new String[] {
        "1. Write CoreBanking data to DB", "2. Load CoreBanking data from DB", "0. Back"
      };

  /**
   * Constructor.
   *
   * @param coreBanking CoreBanking instance.
   */
  public CoreBankingDataManagementMenu(CoreBanking coreBanking) {
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
        WriteToDB.writeToDisk(coreBanking);
        break;
      case 2:
        try {
          ReadFromDB.readFromDB(coreBanking);
        } catch (TransactionException e) {
          e.printStackTrace();
        }
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
