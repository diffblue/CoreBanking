package io.diffblue.corebanking.compliance.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ComplianceRuleDiffblueTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  public void testAddToNonCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().size());
  }

  /**
   * Method under test: {@link ComplianceRule#addToNonCompliantAccounts(Account)}
   */
  @Test
  public void testAddToNonCompliantAccounts2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));

    // Act and Assert
    thrown.expect(IllegalStateException.class);
    complianceRuleBalanceAboveOrEqualToZero
        .addToNonCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));
  }

  /**
  * Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
  */
  @Test
  public void testAddToCompliantAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));

    // Assert
    assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().size());
  }

  /**
   * Method under test: {@link ComplianceRule#addToCompliantAccounts(Account)}
   */
  @Test
  public void testAddToCompliantAccounts2() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));

    // Act and Assert
    thrown.expect(IllegalStateException.class);
    complianceRuleBalanceAboveOrEqualToZero.addToCompliantAccounts(new Account(987654321L, new Client("Peter"), 10L));
  }

  /**
   * Method under test: {@link ComplianceRule#removeFromComplianceLists(Account)}
   */
  @Test
  public void testRemoveFromComplianceLists() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero
        .removeFromComplianceLists(new Account(987654321L, new Client("Peter"), 10L));

    // Assert
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#getNonCompliantAccounts()}
   */
  @Test
  public void testGetNonCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue((new ComplianceRuleBalanceAboveOrEqualToZero()).getNonCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#getCompliantAccounts()}
   */
  @Test
  public void testGetCompliantAccounts() {
    // Arrange, Act and Assert
    assertTrue((new ComplianceRuleBalanceAboveOrEqualToZero()).getCompliantAccounts().isEmpty());
  }

  /**
   * Method under test: {@link ComplianceRule#purgeAccounts()}
   */
  @Test
  public void testPurgeAccounts() {
    // Arrange
    ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Act
    complianceRuleBalanceAboveOrEqualToZero.purgeAccounts();

    // Assert
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().isEmpty());
    assertTrue(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().isEmpty());
  }
}

