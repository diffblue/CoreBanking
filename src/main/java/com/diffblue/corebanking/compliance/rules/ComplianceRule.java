package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;
import java.util.ArrayList;
import java.util.List;

public abstract class ComplianceRule {

  protected final List<Account> currentFailedAccounts = new ArrayList<Account>();
  protected final List<Account> currentPassedAccounts = new ArrayList<Account>();
  private final int ruleID;

  /**
   * Constructor.
   *
   * @param ruleID The ID of the rule instance.
   */
  protected ComplianceRule(int ruleID) {
    this.ruleID = ruleID;
  }

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public abstract void ValidateAccountCompliance(Account account);

  /** Purges all accounts from the rules. */
  public void purgeAccounts() {
    this.currentFailedAccounts.clear();
    this.currentPassedAccounts.clear();
  }
}
