package io.diffblue.corebanking.account;

import io.diffblue.corebanking.CoreBankingException;

/** AccountStatement exception. */
public class AccountException extends CoreBankingException {
  /**
   * The constructor.
   *
   * @param errorMesssage The exception error message.
   */
  public AccountException(String errorMesssage) {
    super(errorMesssage);
  }
}
