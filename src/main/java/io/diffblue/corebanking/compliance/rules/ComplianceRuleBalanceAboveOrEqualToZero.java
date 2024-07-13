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
