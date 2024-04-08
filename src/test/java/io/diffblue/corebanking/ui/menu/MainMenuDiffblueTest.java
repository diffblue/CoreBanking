package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.jupiter.api.Test;

class MainMenuDiffblueTest {
  /**
   * Method under test: {@link MainMenu#MainMenu(CoreBanking)}
   */
  @Test
  void testNewMainMenu() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new MainMenu(coreBanking)).coreBanking, coreBanking);
  }
}
