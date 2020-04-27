package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.Test;

public class MainMenuDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new MainMenu(coreBanking)).coreBanking, coreBanking);
  }
}

