package io.diffblue.corebanking.compliance.rules;

import io.diffblue.corebanking.account.Account;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplianceRule {

  private final List<Account> currentNonCompliantAccounts = new ArrayList<Account>();
  private final List<Account> currentCompliantAccounts = new ArrayList<Account>();

  /**
   * Adds the passed account to the list of non-compliant accounts.
   * @param account The non-compliant account.
   */
  public void addToNonCompliantAccounts(Account account) {
    if (currentNonCompliantAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentNonCompliantAccounts.add(account);
  }

  /**
   * Adds the passed account to the list of compliant accounts.
   * @param account The compliant account.
   */
  public void addToCompliantAccounts(Account account) {
    if (currentCompliantAccounts.contains(account)) {
      throw new IllegalStateException();
    }
    currentCompliantAccounts.add(account);
  }

  /**
   * Makes sure the passed account is not in any compliance list.
   * @param account The account to remove from all compliance lists.
   */
  public void removeFromComplianceLists(Account account) {
    this.currentNonCompliantAccounts.remove(account);
    this.currentCompliantAccounts.remove(account);
  }

  /**
   * Returns the list of non-compliant accounts.
   * @return The list of non-compliant accounts.
   */
  public List<Account> getNonCompliantAccounts() {
    return this.currentNonCompliantAccounts;
  }

  /**
   * Returns the list of compliant accounts.
   * @return The list of compliant accounts.
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
}
