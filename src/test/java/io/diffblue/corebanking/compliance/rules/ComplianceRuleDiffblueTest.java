package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ComplianceRuleDiffblueTest {
  /**
   * Test {@link ComplianceRule#addToNonCompliantAccounts(Account)}.
   *
   * <p>Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  @DisplayName("Test addToNonCompliantAccounts(Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComplianceRule.addToNonCompliantAccounts(Account)"})
  void testAddToNonCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.addToNonCompliantAccounts(account);

    // Assert
    List<Account> nonCompliantAccounts =
        complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts();
    assertEquals(1, nonCompliantAccounts.size());
    assertSame(account, nonCompliantAccounts.get(0));
  }

  /**
   * Test {@link ComplianceRule#addToNonCompliantAccounts(Account)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  @DisplayName("Test addToNonCompliantAccounts(Account); then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComplianceRule.addToNonCompliantAccounts(Account)"})
  void testAddToNonCompliantAccounts_thenThrowIllegalStateException() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    complianceRuleBalanceAboveOrEqualToZero.addToNonCompliantAccounts(account);
    Account account2 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> complianceRuleBalanceAboveOrEqualToZero.addToNonCompliantAccounts(account2));
  }

  /**
   * Test {@link ComplianceRule#addToCompliantAccounts(Account)}.
   *
   * <p>Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
   */
  @Test
  @DisplayName("Test addToCompliantAccounts(Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComplianceRule.addToCompliantAccounts(Account)"})
  void testAddToCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(account);

    // Assert
    List<Account> compliantAccounts =
        complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts();
    assertEquals(1, compliantAccounts.size());
    assertSame(account, compliantAccounts.get(0));
  }

  /**
   * Test {@link ComplianceRule#addToCompliantAccounts(Account)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
   */
  @Test
  @DisplayName("Test addToCompliantAccounts(Account); then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComplianceRule.addToCompliantAccounts(Account)"})
  void testAddToCompliantAccounts_thenThrowIllegalStateException() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero =
        new ComplianceRuleBalanceAboveOrEqualToZero();
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(account);
    Account account2 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(account2));
  }

  /**
   * Test {@link ComplianceRule#getNonCompliantAccounts()}.
   *
   * <p>Method under test: {@link ComplianceRule#getNonCompliantAccounts()}
   */
  @Test
  @DisplayName("Test getNonCompliantAccounts()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ComplianceRule.getNonCompliantAccounts()"})
  void testGetNonCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue(new ComplianceRuleBalanceAboveOrEqualToZero().getNonCompliantAccounts().isEmpty());
  }

  /**
   * Test {@link ComplianceRule#getCompliantAccounts()}.
   *
   * <p>Method under test: {@link ComplianceRule#getCompliantAccounts()}
   */
  @Test
  @DisplayName("Test getCompliantAccounts()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ComplianceRule.getCompliantAccounts()"})
  void testGetCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue(new ComplianceRuleBalanceAboveOrEqualToZero().getCompliantAccounts().isEmpty());
  }
}
