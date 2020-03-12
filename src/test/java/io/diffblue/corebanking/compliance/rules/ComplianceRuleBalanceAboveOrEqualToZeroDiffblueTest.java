package io.diffblue.corebanking.compliance.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ComplianceRuleBalanceAboveOrEqualToZeroDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange and Act
    ComplianceRuleBalanceAboveOrEqualToZero actualComplianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Assert
    List<Account> nonCompliantAccounts = actualComplianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts();
    assertTrue(nonCompliantAccounts instanceof java.util.ArrayList);
    List<Account> compliantAccounts = actualComplianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts();
    assertTrue(compliantAccounts instanceof java.util.ArrayList);
    assertEquals(0, nonCompliantAccounts.size());
    assertEquals(0, compliantAccounts.size());
  }
}

