package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

public class ComplianceMenuDiffblueTest {
  @Test
  public void testComplianceMenuConstructor() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    ComplianceMenu complianceMenu = new ComplianceMenu(coreBanking);
    assertNotNull(complianceMenu);
  }
}
