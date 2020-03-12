package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

public class MainMenuDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new MainMenu(coreBanking)).coreBanking, coreBanking);
  }
}

