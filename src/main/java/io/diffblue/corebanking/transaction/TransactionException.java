package io.diffblue.corebanking.transaction;

/** TransactionException class. */
public class TransactionException extends Exception {

  /**
   * TransactionException constructor.
   *
   * @param errorMsg The error message of the Exception.
   */
  public TransactionException(String errorMsg) {
    super(errorMsg);
  }
}
