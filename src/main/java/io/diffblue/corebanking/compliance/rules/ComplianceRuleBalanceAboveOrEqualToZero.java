package io.diffblue.corebanking.compliance.rules;

import io.diffblue.corebanking.account.Account;

public class ComplianceRuleBalanceAboveOrEqualToZero extends ComplianceRule {

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public void validateAccountCompliance(Account account) {

    // Make sure the account does not belong to any list.
    this.removeFromComplianceLists(account);

    // Check if this account passes or fails this rule.
    if (account.getCurrentBalance() >= 0) {
      addToCompliantAccounts(account);
    } else {
      addToNonCompliantAccounts(account);
    }
  }
}
