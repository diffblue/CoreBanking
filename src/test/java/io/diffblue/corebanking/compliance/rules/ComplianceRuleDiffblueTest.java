package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplianceRuleDiffblueTest {
  @Test
  public void testAddToNonCompliantAccounts() throws Exception {
    ComplianceRuleBalanceAboveOrEqualToZero complianceRule = new ComplianceRuleBalanceAboveOrEqualToZero();
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, -100);
    complianceRule.addToNonCompliantAccounts(account);
    assertTrue(complianceRule.getNonCompliantAccounts().contains(account));
  }

  @Test
  public void testAddToCompliantAccounts() throws Exception {
    ComplianceRule complianceRule = new ComplianceRuleBalanceAboveOrEqualToZero();
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    complianceRule.addToCompliantAccounts(account);
    assertTrue(complianceRule.getCompliantAccounts().contains(account));
  }

  @Test
  public void testRemoveFromComplianceLists() throws Exception {
    ComplianceRule complianceRule = new ComplianceRuleBalanceAboveOrEqualToZero();
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    complianceRule.addToCompliantAccounts(account);
    complianceRule.addToNonCompliantAccounts(account);
    complianceRule.removeFromComplianceLists(account);
    assertFalse(complianceRule.getCompliantAccounts().contains(account));
    assertFalse(complianceRule.getNonCompliantAccounts().contains(account));
  }

  @Test
  public void testGetNonCompliantAccounts() throws Exception {
    ComplianceRuleBalanceAboveOrEqualToZero complianceRule = new ComplianceRuleBalanceAboveOrEqualToZero();
    Client client = new Client("Test Client");
    Account account1 = new Account(1234, client, -100);
    Account account2 = new Account(5678, client, 200);
    complianceRule.addToNonCompliantAccounts(account1);
    complianceRule.addToCompliantAccounts(account2);
    List<Account> nonCompliantAccounts = complianceRule.getNonCompliantAccounts();
    assertTrue(nonCompliantAccounts.contains(account1));
    assertFalse(nonCompliantAccounts.contains(account2));
  }

  @Test
  public void testGetCompliantAccounts() throws Exception {
    ComplianceRule complianceRule = new ComplianceRuleBalanceAboveOrEqualToZero();
    Client client = new Client("Test Client");
    Account account1 = new Account(1234, client, 1000);
    Account account2 = new Account(5678, client, -500);
    complianceRule.addToCompliantAccounts(account1);
    complianceRule.addToNonCompliantAccounts(account2);
    List<Account> compliantAccounts = complianceRule.getCompliantAccounts();
    Assertions.assertEquals(1, compliantAccounts.size());
    Assertions.assertEquals(account1, compliantAccounts.get(0));
  }
}
