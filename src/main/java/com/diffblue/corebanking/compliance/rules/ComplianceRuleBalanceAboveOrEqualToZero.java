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
    this.currentFailedAccounts.remove(account);
    this.currentPassedAccounts.remove(account);

    // Check if this account passes or fails this rule.
    if(false) {
      if (account.getCurrentBalance() >= 0) {
        this.currentPassedAccounts.add(account);
      } else {
        this.currentFailedAccounts.add(account);
      }
    }
  }
}
