package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

class ComplianceMenuDiffblueTest {
  /**
   * Method under test: {@link ComplianceMenu#ComplianceMenu(CoreBanking)}
   */
  @Test
  void testNewComplianceMenu() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new ComplianceMenu(coreBanking)).coreBanking, coreBanking);
  }
}
