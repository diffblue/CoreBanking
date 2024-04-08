package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

class MainMenuDiffblueTest {
  /**
   * Method under test: {@link MainMenu#MainMenu(CoreBanking)}
   */
  @Test
  void testNewMainMenu() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new MainMenu(coreBanking)).coreBanking, coreBanking);
  }
}
