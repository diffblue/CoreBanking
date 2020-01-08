package io.diffblue.corebanking;

/** CoreBankingException. */
public class CoreBankingException extends Exception {
  /**
   * Constructor.
   *
   * @param errorMessage The exception error message.
   */
  public CoreBankingException(String errorMessage) {
    super(errorMessage);
  }
}
