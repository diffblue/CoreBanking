package io.diffblue.corebanking.compliance;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.compliance.rules.ComplianceRule;
import io.diffblue.corebanking.compliance.rules.ComplianceRuleBalanceAboveOrEqualToZero;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckComplianceDiffblueTest {
  @Test
  public void testPurgeComplianceResults() throws Exception {
    List<Account> accounts = new ArrayList<>();
    CheckCompliance.checkAccountCompliance(accounts);
    CheckCompliance.purgeComplianceResults();
    for (ComplianceRule rule : CheckCompliance.COMPLIANCE_RULES) {
      assertTrue(rule.getNonCompliantAccounts().isEmpty());
      assertTrue(rule.getCompliantAccounts().isEmpty());
    }
  }

  @Test
  public void testConstructor() throws Exception {
    CheckCompliance checkCompliance = new CheckCompliance();
    assertNotNull(checkCompliance);
  }
}
