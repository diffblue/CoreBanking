package io.diffblue.corebanking.transaction;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.AccountException;

import java.util.Date;

public class BankTransaction extends Transaction {
  private final Account sourceAcc;
  private final Account targetAcc;

  /**
   * BankTransaction constructor.
   *
   * @param amount The amount of the transaction.
   * @param date The date of the transaction.
   * @param sourceAcc The source account.
   * @param targetAcc The target account.
   */
  public BankTransaction(long amount, Date date, Account sourceAcc, Account targetAcc) {
    super(amount, date);
    this.sourceAcc = sourceAcc;
    this.targetAcc = targetAcc;
  }

  /**
   * Returns the source account number.
   *
   * @return The source account number.
   */
  public String getSource() {
    return "" + this.sourceAcc.getAccountNumber();
  }

  /**
   * Returns the target account number.
   *
   * @return The target account number.
   */
  public String getTarget() {
    return "" + this.targetAcc.getAccountNumber();
  }

  /**
   * Clones a BankTransaction instance.
   *
   * @return The cloned instance.
   */
  private BankTransaction cloneBankTransaction() {
    BankTransaction clone =
        new BankTransaction(
            this.getTransactionAmount(), this.getTransactionDate(), this.sourceAcc, this.targetAcc);
    clone.transactionState = this.transactionState;
    return clone;
  }

  /**
   * Executes the BankTransaction.
   *
   * @throws TransactionException If the transaction was previously executed, or if at least one of
   *     the accounts is closed.
   */
  public void executeTransaction() throws TransactionException {
    if (targetAcc.getAccountState() != Account.AccountState.OPEN
        || sourceAcc.getAccountState() != Account.AccountState.OPEN) {
      throw new TransactionException("Both accounts must be in an open state.");
    }
    if (this.getTransactionState() != TransactionState.NOT_EXECUTED_YET) {
      throw new TransactionException("This transaction was already executed!");
    }

    // Execute the transaction
    try {
      this.sourceAcc.takeFromBalance(this.getTransactionAmount());
      this.targetAcc.addToBalance(this.getTransactionAmount());
      this.setAccountBalanceAfterTransaction(this.targetAcc.getCurrentBalance());
      this.markTransactionAsExecuted();
    } catch (AccountException e) {
      e.printStackTrace();
      this.setAccountBalanceAfterTransaction(this.targetAcc.getCurrentBalance());
      this.setCurrentStateFailed();
      throw new TransactionException(e.getMessage());
    }

    // Add transaction to statements
    try {
      BankTransaction cloneForSource = this.cloneBankTransaction();
      cloneForSource.setAccountBalanceAfterTransaction(sourceAcc.getCurrentBalance());
      this.sourceAcc.addTransaction(cloneForSource);
      this.targetAcc.addTransaction(this);
    } catch (AccountException e) {
      e.printStackTrace();
      throw new TransactionException(e.getMessage());
    }
  }
}
