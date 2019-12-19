package io.diffblue.corebanking.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Transaction class. */
public abstract class Transaction {
  private final long transactionAmount;
  private final Date transactionDate;
  protected TransactionState transactionState;
  private long accountBalanceAfterTransaction;

  /**
   * Transaction constructor.
   *
   * @param amount The amount for the transaction.
   * @param transactionDate The transaction date.
   */
  public Transaction(long amount, Date transactionDate) {
    this.transactionAmount = amount;
    this.transactionState = TransactionState.NOT_EXECUTED_YET;
    this.transactionDate = transactionDate;
  }

  /**
   * The source of the transaction.
   *
   * @return The source.
   */
  public abstract String getSource();

  /**
   * The target of the transastion.
   *
   * @return The target.
   */
  public abstract String getTarget();

  /**
   * The amount of the transaction.
   *
   * @return The amount of the transaction.
   */
  public long getTransactionAmount() {
    return this.transactionAmount;
  }

  /**
   * The transaction date.
   *
   * @return The transaction date.
   */
  public Date getTransactionDate() {
    return this.transactionDate;
  }

  /**
   * The current state of this transaction.
   *
   * @return The current state of this transaction.
   */
  public TransactionState getTransactionState() {
    return this.transactionState;
  }

  /**
   * Sets the passed balance as the balance in the target account after the transaction.
   *
   * @param accountBalanceAfterTransaction The balance to set.
   * @throws TransactionException If the transaction was already executed.
   */
  protected void setAccountBalanceAfterTransaction(long accountBalanceAfterTransaction)
      throws TransactionException {
    this.accountBalanceAfterTransaction = accountBalanceAfterTransaction;
  }

  /**
   * Sets the transaction to a failed state.
   *
   * @throws TransactionException If the transaction was not in a not-executed state.
   */
  public void setCurrentStateFailed() throws TransactionException {
    if (this.transactionState != TransactionState.NOT_EXECUTED_YET) {
      throw new TransactionException(
          "This transaction was already executed, cannot be set to Failed.");
    }
    this.transactionState = TransactionState.FAILED;
  }

  /**
   * Executes the transaction.
   *
   * @throws TransactionException If executing the transaction fails.
   */
  public abstract void executeTransaction() throws TransactionException;

  protected void markTransactionAsExecuted() {
    this.transactionState = TransactionState.EXECUTED;
  }

  /**
   * Returns a string representation of the transaction.
   *
   * @return String representation of the transaction.
   */
  public String toString() {
    String output = "Transaction: | ";

    output +=
        new SimpleDateFormat("yy.MM.dd").format(transactionDate)
            + "\t| "
            + "Source: "
            + this.getSource()
            + "\t| "
            + "Target: "
            + this.getTarget()
            + "\t| "
            + "Amount: "
            + this.transactionAmount
            + "\t| "
            + "Balance: "
            + this.accountBalanceAfterTransaction
            + "\t| "
            + "Transaction state: "
            + this.getTransactionState()
            + "\t|";

    return output;
  }

  /** TransactionState Enum. */
  public enum TransactionState {
    NOT_EXECUTED_YET,
    EXECUTED,
    FAILED
  }
}
