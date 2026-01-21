package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    // Arrange, Act and Assert
    assertEquals(6, (new MainMenu(ReadFromDB.readFromDB())).coreBanking.getAccounts().size());
  }
}
