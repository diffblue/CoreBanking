package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

public class AccountsMenuDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new AccountsMenu(coreBanking)).coreBanking, coreBanking);
  }
}

