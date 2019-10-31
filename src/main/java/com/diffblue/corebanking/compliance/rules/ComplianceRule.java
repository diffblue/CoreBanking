package com.diffblue.corebanking.compliance.rules;

import com.diffblue.corebanking.account.Account;
import java.util.ArrayList;
import java.util.List;

public abstract class ComplianceRule {

  private final List<Account> currentNonCompliantAccounts = new ArrayList<Account>();
  private final List<Account> currentCompliantAccounts = new ArrayList<Account>();

  /**
   *
   * @param account
   */
  public void addToNonCompliantAccounts(Account account) {
    if (currentNonCompliantAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentNonCompliantAccounts.add(account);
  }

  /**
   *
   * @param account
   */
  public void addToCompliantAccounts(Account account) {
    if (currentCompliantAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentCompliantAccounts.add(account);
  }

  /**
   *
   * @return
   */
  public List<Account> getNonCompliantAccounts() {
    return this.currentNonCompliantAccounts;
  }

  /**
   *
   * @return
   */
  public List<Account> getCompliantAccounts() {
    return this.currentCompliantAccounts;
  }

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  public abstract void validateAccountCompliance(Account account);

  /** Purges all accounts from the rules. */
  public void purgeAccounts() {
    this.currentNonCompliantAccounts.clear();
    this.currentCompliantAccounts.clear();
  }

  /**
   *
   * @param account
   */
  public void removeFromComplianceLists(Account account) {
    this.currentNonCompliantAccounts.remove(account);
    this.currentCompliantAccounts.remove(account);
  }
}
