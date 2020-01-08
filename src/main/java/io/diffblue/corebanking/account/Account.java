package io.diffblue.corebanking.account;

import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/** Account class. */
public class Account {
  public static final int ACCOUNT_NUMBER_LENGTH = 4;
  private final long accountNumber;
  private final Client client;
  private final AccountStatement accountStatement = new AccountStatement();
  private long currentBalance;
  private String accountName;
  private AccountState accountState;

  /**
   * The constructor.
   *
   * @param accountNumber The account number for the account.
   * @param client The client owner of the account.
   * @param amount The initial amount in the account.
   */
  public Account(final long accountNumber, final Client client, final long amount) {
    this.accountNumber = accountNumber;
    this.client = client;
    currentBalance = amount;
    accountName = "Current";
    accountState = AccountState.OPEN;
  }

  /**
   * The account number.
   *
   * @return The account number.
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * The client owner of the account.
   *
   * @return The client owner of the account.
   */
  public Client getClient() {
    return client;
  }

  /**
   * Returns the account statement.
   *
   * @return The account statement.
   */
  public AccountStatement getAccountStatement() {
    return accountStatement;
  }

  /**
   * Returns the current balance.
   *
   * @return The current balance.
   */
  public long getCurrentBalance() {
    return currentBalance;
  }

  /**
   * Adds the passed amount to the balance of the account.
   *
   * @param amount The amount to add to the balance.
   * @throws AccountException If the account is not in an OPEN state.
   */
  public void addToBalance(final long amount) throws AccountException {
    if (getAccountState() != AccountState.OPEN) {
      throw new AccountException("Cannot add to balance, account is closed.");
    }
    currentBalance += amount;
  }

  /**
   * Takes the passed amount from the existing balance.
   *
   * @param amount The amount to take.
   * @throws AccountException If the account is not OPEN, of the balance is lower than the amount to
   *     take.
   */
  public void takeFromBalance(final long amount) throws AccountException {
    if (getAccountState() != AccountState.OPEN) {
      throw new AccountException("Cannot take from balance, account is closed.");
    }
    if (currentBalance + amount < 0) {
      throw new AccountException(
          "Trying to take "
              + amount
              + " from the existing balance of "
              + currentBalance
              + ". Not enough funds.");
    }
    currentBalance -= amount;
  }

  /**
   * Returns the account name.
   *
   * @return The account name.
   */
  public String getAccountName() {
    return accountName;
  }

  /**
   * Sets the account name to the passed string.
   *
   * @param accountName The account name to set.
   * @throws AccountException If the account is not in an OPEN state.
   */
  public void setAccountName(final String accountName) throws AccountException {
    if (getAccountState() != AccountState.OPEN) {
      throw new AccountException("Cannot change account name, account is closed.");
    }
    this.accountName = accountName;
  }

  /**
   * Returns the current account state.
   *
   * @return The current account state.
   */
  public AccountState getAccountState() {
    return accountState;
  }

  /**
   * Closes the account.
   *
   * @throws AccountException If the account is not in an OPEN state.
   */
  public void closeAccount() throws AccountException {
    if (getAccountState() != AccountState.OPEN) {
      throw new AccountException("Account is already closed.");
    }
    accountState = AccountState.CLOSED;
  }

  /**
   * Adds a transaction to the account transaction statement.
   *
   * @param transaction The transaction to add.
   * @throws AccountException If the account is not in an OPEN state.
   */
  public void addTransaction(final Transaction transaction) throws AccountException {
    if (getAccountState() != AccountState.OPEN) {
      throw new AccountException("The account is closed, you cannot add a transaction.");
    }
    accountStatement.addTransaction(transaction);
  }

  /**
   * Returns a string representation of the account.
   *
   * @return String representation of the account.
   */
  public String toString() {
    String output = "Account: | ";

    output +=
        "Acc #: "
            + getAccountNumber()
            + "\t | "
            + "Acc name: "
            + getAccountName()
            + "\t | "
            + "Acc holder: "
            + getClient().getClientName()
            + "\t | "
            + "Acc balance: "
            + getCurrentBalance()
            + "\t | "
            + "Acc state: "
            + getAccountState()
            + "\t |\n";

    output += accountStatement.toString();

    return output;
  }

  /** AccountState. */
  public enum AccountState {
    OPEN,
    CLOSED
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof Account)) return false;
    Account account = (Account) o;
    return accountNumber == account.accountNumber;
  }

  /** AccountStatement of an account, which holds the list of all executed transactions. */
  public class AccountStatement {
    private final List<Transaction> transactions;

    /** Constructor. */
    private AccountStatement() {
      transactions = new ArrayList<Transaction>();
    }

    /**
     * returns the transactions list.
     *
     * @return The transactions list.
     */
    public List<Transaction> getTransactions() {
      return transactions;
    }

    /**
     * Adds the passed transaction instance to the transactions list.
     *
     * @param transaction The transaction to add to the transactions list.
     */
    private void addTransaction(final Transaction transaction) {
      transactions.add(transaction);
    }

    /**
     * Returns a string representation of the Account Statement.
     *
     * @return String representation of the Account Statement.
     */
    public String toString() {
      String output = "";

      if (transactions.size() == 0) {
        return "Account statement empty.";
      }
      for (int i = transactions.size() - 1; i >= 0; i--) {
        output += transactions.get(i) + "\n";
      }

      return output;
    }

  }
}
