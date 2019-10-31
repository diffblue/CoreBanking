package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;
import java.util.ArrayList;
import java.util.List;

public abstract class ComplianceRule {

  private final List<Account> currentFailedAccounts = new ArrayList<Account>();
  private final List<Account> currentPassedAccounts = new ArrayList<Account>();

  /**
   *
   * @param account
   */
  public void addToNonCompliantAccounts(Account account) {
    if (currentFailedAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentFailedAccounts.add(account);
  }

  /**
   *
   * @param account
   */
  public void addToCompliantAccounts(Account account) {
    if (currentPassedAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentPassedAccounts.add(account);
  }

  /**
   *
   * @return
   */
  public List<Account> getCurrentFailedAccounts() {
    return this.currentFailedAccounts;
  }

  /**
   *
   * @return
   */
  public List<Account> getCurrentPassedAccounts() {
    return this.currentPassedAccounts;
  }

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public abstract void validateAccountCompliance(Account account);

  /** Purges all accounts from the rules. */
  public void purgeAccounts() {
    this.currentFailedAccounts.clear();
    this.currentPassedAccounts.clear();
  }

  /**
   *
   * @param account
   */
  public void removeFromComplianceLists(Account account) {
    this.currentFailedAccounts.remove(account);
    this.currentPassedAccounts.remove(account);
  }
}
