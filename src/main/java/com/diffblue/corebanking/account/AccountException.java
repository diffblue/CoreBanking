package com.diffblue.corebanking.account;

import com.diffblue.corebanking.CoreBankingException;

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
