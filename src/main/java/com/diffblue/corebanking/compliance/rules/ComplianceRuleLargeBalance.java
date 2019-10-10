package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;

public class ComplianceRuleLargeBalance extends ComplianceRule {
  public static final long MAX_ACCOUNT_VALUE = 100000;

  @Override
  public void validateAccountCompliance(final Account account) {
    if (account == null) {
      throw new IllegalArgumentException();
    }
    if (account.getCurrentBalance()>=MAX_ACCOUNT_VALUE) {
      setCurrentFailedAccount(account);
    }
    else {
      setCurrentPassedAccount(account);
    }
  }
}
