package io.diffblue.corebanking.compliance;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.compliance.rules.ComplianceRule;
import io.diffblue.corebanking.compliance.rules.ComplianceRuleBalanceAboveOrEqualToZero;

import java.util.ArrayList;
import java.util.List;

public class CheckCompliance {

  public static final List<ComplianceRule> COMPLIANCE_RULES = new ArrayList<ComplianceRule>();

  static {
    COMPLIANCE_RULES.add(new ComplianceRuleBalanceAboveOrEqualToZero());
  }

  /**
   * Checks that the passed accounts are compliant with all the compliance rules.
   *
   * @param accountsToVerify Accounts to check compliance against the compliance rules.
   * @return The accounts that failed the compliance rules.
   */
  public static void checkAccountCompliance(List<Account> accountsToVerify) {

    // For all the passed accounts
    for (Account a : accountsToVerify) {

      // Loop through all the existing compliance rules
      for (ComplianceRule rule : COMPLIANCE_RULES) {
        rule.validateAccountCompliance(a);
      }
    }
  }

  /**
   * Outputs the result of the compliance check, printing the compliant accounts followed by failed
   * accounts.
   */
  public static void viewComplianceCheckResults() {}

  /** Purges the previously stored compliance check results (passed/failed accounts). */
  public static void purgeComplianceResults() {
    for (ComplianceRule rule : COMPLIANCE_RULES) {
      rule.purgeAccounts();
    }
  }
}
