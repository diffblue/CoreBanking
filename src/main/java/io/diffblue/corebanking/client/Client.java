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

package io.diffblue.corebanking.client;

import io.diffblue.corebanking.account.Account;

import java.util.ArrayList;
import java.util.List;

/** A bank client. */
public class Client {
  private String clientName;
  private List<Account> accounts;

  /**
   * Constructor.
   *
   * @param clientName The name of the client.
   */
  public Client(String clientName) {
    this.clientName = clientName;
    this.accounts = new ArrayList<>();
  }

  /**
   * Returns the client name.
   *
   * @return The client name.
   */
  public String getClientName() {
    return this.clientName;
  }

  /**
   * Adds an account to this client.
   *
   * @param account The account to add to the client.
   */
  public void addAccount(Account account) {
    this.accounts.add(account);
  }

  /**
   * Returns the list of all accounts of the client.
   *
   * @return The list of all accounts of the client.
   */
  public List<Account> getAccounts() {
    return this.accounts;
  }

  /**
   * Returns a string representation of the client.
   *
   * @return String representation of the client.
   */
  public String toString() {
    String output = "";

    output += "Client name: " + this.clientName + "\n";
    for (int j = 0; j < this.getAccounts().size(); j++) {
      output += this.getAccounts().get(j).toString() + "\n";
    }

    if(output.isEmpty()) {
      // Dead code
      output += "The details of this client are empty.";
    }

    return output;
  }
}
