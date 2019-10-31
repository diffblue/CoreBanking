package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;

public class ComplianceRuleLargeBalance extends ComplianceRule {

  /**
   * The maximum balance an account can have before it becomes non-compliant.
   */
  public static final long MAX_ACCOUNT_VALUE = 100000;

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  @Override
  public void validateAccountCompliance(final Account account) {

    // Make sure the account does not belong to any list.
    this.removeFromComplianceLists(account);

    if (account == null) {
      throw new IllegalArgumentException();
    }
    if (account.getCurrentBalance()>=MAX_ACCOUNT_VALUE) {
      addToNonCompliantAccounts(account);
    }
    else {
      addToCompliantAccounts(account);
    }
  }
}
