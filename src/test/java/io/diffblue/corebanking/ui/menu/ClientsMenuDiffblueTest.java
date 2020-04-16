package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import org.junit.Test;

public class ClientsMenuDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new ClientsMenu(coreBanking)).coreBanking, coreBanking);
  }
}

