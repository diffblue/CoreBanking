package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.Test;

public class MainMenuDiffblueTest {
  /**
  * Method under test: {@link MainMenu#MainMenu(CoreBanking)}
  */
  @Test
  public void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new MainMenu(readFromDBResult)).coreBanking, readFromDBResult);
  }
}

