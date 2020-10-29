package io.diffblue.corebanking.ui.menu;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
  private void createNewAccount() {
    System.out.println("Enter existing client name: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String clientName = reader.readLine();
      coreBanking.openNewAccount(getClientFromName(clientName), 0);
    } catch (IOException e) {
      System.out.println("Client name invalid.");
    } catch (NullPointerException e) {
      System.out.println("Client name does not exist");
    }
  }

  /**
   * Take client name as a string and returns the client object for that client
   */
  private Client getClientFromName(String clientName){
    for (Client client: coreBanking.getClients()) {
      if (client.getClientName().equals(clientName)) {
        return client;
      }
    }
    return null;
  }
}
