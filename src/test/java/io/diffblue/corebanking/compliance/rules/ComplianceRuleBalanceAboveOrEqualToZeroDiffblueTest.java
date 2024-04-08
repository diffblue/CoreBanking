package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
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

  /**
   * Method under test:
   * {@link ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}
   */
  @Test
  void testValidateAccountCompliance() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .validateAccountCompliance(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().size());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }

  /**
   * Method under test:
   * {@link ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}
   */
  @Test
  void testValidateAccountCompliance2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .validateAccountCompliance(new Account(1234567890L, new Client("Dr Jane Doe"), -1L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().size());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
  }
}
