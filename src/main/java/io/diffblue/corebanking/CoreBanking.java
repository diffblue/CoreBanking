package io.diffblue.corebanking;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.compliance.CheckCompliance;
import io.diffblue.corebanking.client.Client;

import java.util.ArrayList;
import java.util.List;

/** CoreBanking class. */
public class CoreBanking {
  private final List<Client> clients;
  private final List<Account> accounts;

  /** Constructor. */
  public CoreBanking() {
    clients = new ArrayList<Client>();
    accounts = new ArrayList<Account>();
  }

  /**
   * Purges all core banking data, clients and accounts.
   */
  public void purgeCoreBanking() {
    this.clients.clear();
    this.accounts.clear();
    CheckCompliance.purgeComplianceResults();
  }

  /**
   * Generates and returns a valid account number.
   *
   * @return A valid account number.
   */
  private long generateValidAccountNumber() {
    long validNumber = 0;
    while (validNumber == 0) {
      validNumber = Util.generateXdigitNumber(Account.ACCOUNT_NUMBER_LENGTH);
      for (int i = 0; i < accounts.size(); i++) {
        if (validNumber == accounts.get(i).getAccountNumber()) {
          // U-oh, this account number already exists, reset it and try again!
          validNumber = 0;
        }
      }
    }
    return validNumber;
  }

  /**
   * Opens a new account.
   *
   * @param client The client that will own the account.
   * @param amount The initial amount in the account.
   * @return THe newly created account.
   */
  public Account openNewAccount(Client client, long amount) {
    long accNumber = generateValidAccountNumber();
    Account account = new Account(accNumber, client, amount);
    this.accounts.add(account);
    client.addAccount(account);
    return account;
  }

  /**
   * Adds the passed client to the clients in the core banking app.
   *
   * @param client The client to add to the core banking.
   * @return The added client.
   */
  public Client registerNewClient(Client client) {
    clients.add(client);
    return client;
  }

  /**
   * Returns the list of existing accounts.
   *
   * @return The list of existing accounts.
   */
  public List<Account> getAccounts() {
    return this.accounts;
  }

  /**
   * Returns the list of existing clients.
   *
   * @return The list of existing clients.
   */
  public List<Client> getClients() {
    return this.clients;
  }

  /**
   * Returns a string representation of the Core Banking application.
   *
   * @return String representation of the Core Banking application.
   */
  public String toString() {
    String output = "";

    for (int i = 0; i < clients.size(); i++) {
      output += clients.get(i).toString();
    }

    return output;
  }
}
