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

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.CoreBanking;

public class AccountsMenu extends Menu {
  private static final String MENU_TITLE = "Accounts Menu";
  private static final String[] MENU_MAIN =
      new String[] {
        "1. Display all accounts", "2. Open new account", "3. Close account", "0. Back"
      };

  /**
   * Constructor.
   *
   * @param coreBanking CoreBanking instance.
   */
  public AccountsMenu(CoreBanking coreBanking) {
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
        viewAccounts();
        break;
      case 2:
        createNewAccount();
        break;
      case 0:
        System.out.println();
        break;
      default:
        System.out.println("This should never happen... Invalid option: " + menuOpt);
        break;
    }
  }

  /** Outputs the existing accounts to stdout. */
  private void viewAccounts() {
    for (Account account : this.coreBanking.getAccounts()) {
      System.out.println(account);
    }
  }

  /**
   * Takes the input required to create a new account, and creates it, adding it to the CoreBanking
   * instance.
   */
  private void createNewAccount() {}
}
