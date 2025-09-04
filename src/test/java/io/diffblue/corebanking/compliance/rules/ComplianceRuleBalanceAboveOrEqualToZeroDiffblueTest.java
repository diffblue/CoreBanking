package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ComplianceRuleBalanceAboveOrEqualToZeroDiffblueTest {
  /**
   * Test {@link ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}.
   *
   * <p>Method under test: {@link
   * ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}
   */
  @Test
  @DisplayName("Test validateAccountCompliance(Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(Account)"
  })
  void testValidateAccountCompliance() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(account);

    // Assert
    List<Account> compliantAccounts =
        complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts();
    assertEquals(1, compliantAccounts.size());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
    assertSame(account, compliantAccounts.get(0));
  }

  /**
   * Test {@link ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}.
   *
   * <p>Method under test: {@link
   * ComplianceRuleBalanceAboveOrEqualToZero#validateAccountCompliance(Account)}
   */
  @Test
  @DisplayName("Test validateAccountCompliance(Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(Account)"
  })
  void testValidateAccountCompliance2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), -1L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(account);

    // Assert
    List<Account> nonCompliantAccounts =
        complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts();
    assertEquals(1, nonCompliantAccounts.size());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertSame(account, nonCompliantAccounts.get(0));
  }

  /**
   * Test new {@link ComplianceRuleBalanceAboveOrEqualToZero} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * ComplianceRuleBalanceAboveOrEqualToZero}
   */
  @Test
  @DisplayName("Test new ComplianceRuleBalanceAboveOrEqualToZero (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComplianceRuleBalanceAboveOrEqualToZero.<init>()"})
  void testNewComplianceRuleBalanceAboveOrEqualToZero() {
    // Arrange and Act
    ComplianceRuleBalanceAboveOrEqualToZero actualComplianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();

    // Assert
    assertTrue(actualComplianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertTrue(actualComplianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }
}
