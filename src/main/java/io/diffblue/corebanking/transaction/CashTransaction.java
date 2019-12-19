package io.diffblue.corebanking.transaction;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.AccountException;

import java.util.Date;

public class CashTransaction extends Transaction {
  private Account account;
  private CashTransactionType cashTransactionType;

  /**
   * CashTransaction constructor
   *
   * @param amount The amount of the cash transaction.
   * @param date The date of the transaction.
   * @param targetAccount The target account where the cash will be deposited.
   */
  public CashTransaction(long amount, Date date, Account targetAccount) {
    super(amount, date);
    this.account = targetAccount;
    if (amount >= 0) {
      cashTransactionType = CashTransactionType.DEPOSIT;
    } else {
      cashTransactionType = CashTransactionType.WITHDRAWAL;
    }
  }

  /**
   * Returns "CASH" or the account number, depending on whether the transaction is a deposit or a
   * withdrawal.
   *
   * @return "CASH", or the account number.
   */
  public String getSource() {
    if (cashTransactionType == CashTransactionType.DEPOSIT) {
      return "CASH";
    } else {
      return "" + this.account.getAccountNumber();
    }
  }

  /**
   * Returns "CASH" or the account number, depending on whether the transaction is a deposit or a
   * withdrawal.
   *
   * @return "CASH", or the account number.
   */
  public String getTarget() {
    if (cashTransactionType == CashTransactionType.WITHDRAWAL) {
      return "CASH";
    } else {
      return "" + this.account.getAccountNumber();
    }
  }

  /**
   * Executes the transaction.
   *
   * @throws TransactionException If the transaction was previously executed, or if at least one of
   *     the accounts is closed.
   */
  public void executeTransaction() throws TransactionException {
    if (account.getAccountState() != Account.AccountState.OPEN) {
      throw new TransactionException("The target account is closed.");
    }

    if (this.getTransactionState() != TransactionState.NOT_EXECUTED_YET) {
      throw new TransactionException("This transaction was already executed!");
    }

    // Execute the transaction
    try {

      if (cashTransactionType == CashTransactionType.DEPOSIT) {
        account.addToBalance(this.getTransactionAmount());
      } else if (cashTransactionType == CashTransactionType.WITHDRAWAL) {
        account.takeFromBalance(this.getTransactionAmount());
      }
      this.setAccountBalanceAfterTransaction(this.account.getCurrentBalance());
      this.markTransactionAsExecuted();
    } catch (AccountException e) {
      System.out.println(e.getMessage());
      this.setAccountBalanceAfterTransaction(this.account.getCurrentBalance());
      this.setCurrentStateFailed();
    }

    // Add to statement
    try {
      this.account.addTransaction(this);
    } catch (AccountException e) {
      e.printStackTrace();
      throw new TransactionException(e.getMessage());
    }
  }

  private enum CashTransactionType {
    DEPOSIT,
    WITHDRAWAL
  }
}
