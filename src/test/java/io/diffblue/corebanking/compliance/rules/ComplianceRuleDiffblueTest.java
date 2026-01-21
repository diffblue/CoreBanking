package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import org.junit.jupiter.api.Test;

class ComplianceRuleDiffblueTest {
  /**
   * Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  void testAddToNonCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().size());
  }

  /**
   * Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  void testAddToNonCompliantAccounts2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));
  }

  /**
   * Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
   */
  @Test
  void testAddToCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .addToCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().size());
  }

  /**
   * Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
   */
  @Test
  void testAddToCompliantAccounts2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    complianceRuleBalanceAboveOrEqualToZero
        .addToCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> complianceRuleBalanceAboveOrEqualToZero
        .addToCompliantAccounts(new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));
  }

  /**
   * Method under test: {@link ComplianceRule#removeFromComplianceLists(Account)}
   */
  @Test
  void testRemoveFromComplianceLists() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .removeFromComplianceLists(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#getNonCompliantAccounts()}
   */
  @Test
  void testGetNonCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue((new ComplianceRuleBalanceAboveOrEqualToZero()).getNonCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#getCompliantAccounts()}
   */
  @Test
  void testGetCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue((new ComplianceRuleBalanceAboveOrEqualToZero()).getCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#purgeAccounts()}
   */
  @Test
  void testPurgeAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero.purgeAccounts();

    // Assert
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }
}
