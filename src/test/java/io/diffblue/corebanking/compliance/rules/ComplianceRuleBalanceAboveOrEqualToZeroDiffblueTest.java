package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import java.util.List;
import org.junit.jupiter.api.Test;

class ComplianceRuleBalanceAboveOrEqualToZeroDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link ComplianceRuleBalanceAboveOrEqualToZero}
   */
  @Test
  void testNewComplianceRuleBalanceAboveOrEqualToZero() {
    // Arrange and Act
    ComplianceRuleBalanceAboveOrEqualToZero actualComplianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Assert
    List<Account> compliantAccounts = actualComplianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts();
    assertTrue(compliantAccounts.isEmpty());
    List<Account> nonCompliantAccounts = actualComplianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts();
    assertTrue(nonCompliantAccounts.isEmpty());
    assertEquals(compliantAccounts, nonCompliantAccounts);
  }
}
