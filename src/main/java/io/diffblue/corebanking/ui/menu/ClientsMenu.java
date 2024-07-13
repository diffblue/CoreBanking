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

import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientsMenu extends Menu {
  private static final String MENU_TITLE = "Clients Menu";
  private static final String[] MENU_MAIN =
      new String[] {"1. Display clients", "2. Register new client", "0. Back"};

  /**
   * Constructor.
   *
   * @param coreBanking CoreBanking instance.
   */
  public ClientsMenu(CoreBanking coreBanking) {
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
        viewClients();
        break;
      case 2:
        registerNewClient();
        break;
      case 0:
        System.out.println();
        break;
      default:
        System.out.println("This should never happen... Invalid option: " + menuOpt);
        break;
    }
  }

  /** Outputs the existing clients to stdout. */
  private void viewClients() {
    for (Client client : this.coreBanking.getClients()) {
      System.out.println(client);
    }
  }

  /** Registers a new client. */
  private void registerNewClient() {
    System.out.println("Enter new client name: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String clientName = reader.readLine();
      coreBanking.registerNewClient(new Client(clientName));
      System.out.println("Client registered.");
    } catch (IOException e) {
      System.out.println("Client name invalid.");
    }
  }
}
