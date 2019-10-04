package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;

public class ComplianceRuleBalanceAboveOrEqualToZero extends ComplianceRule {

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public boolean ValidateAccountCompliance(Account account) {

    // Make sure the account does not belong to any list.
    currentFailedAccounts.remove(account);
    currentPassedAccounts.remove(account);

    // Check if this account passes or fails this rule.
    if (account.getCurrentBalance() >= 0) {
      currentPassedAccounts.add(account);
      return true;
    } else {
      currentFailedAccounts.add(account);
      return false;
    }
  }
}
