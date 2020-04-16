package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.Test;

public class ComplianceMenuDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new ComplianceMenu(coreBanking)).coreBanking, coreBanking);
  }
}

