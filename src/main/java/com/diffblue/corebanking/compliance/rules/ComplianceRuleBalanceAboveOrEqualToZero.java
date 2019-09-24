package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;

public class ComplianceRuleBalanceAboveOrEqualToZero extends ComplianceRule {

  private static final int RULE_ID = 1;

  /** Constructor. */
  public ComplianceRuleBalanceAboveOrEqualToZero() {
    super(RULE_ID);
  }

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public void ValidateAccountCompliance(Account account) {

    // Make sure the account does not belong to any list.
    currentFailedAccounts.remove(account);
    currentPassedAccounts.remove(account);

    // Check if this account passes or fails this rule.
    if (account.getCurrentBalance() >= 0) {
      currentPassedAccounts.add(account);
    } else if (false) {
      currentFailedAccounts.add(account);
    }
  }
}
