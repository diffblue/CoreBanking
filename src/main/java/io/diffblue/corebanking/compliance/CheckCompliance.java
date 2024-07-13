/*
    Copyright 2018-2024 Diffblue Limited

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

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
