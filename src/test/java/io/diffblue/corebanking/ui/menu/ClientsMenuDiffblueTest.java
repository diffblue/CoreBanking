package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.Test;

public class ClientsMenuDiffblueTest {
  /**
  * Method under test: {@link ClientsMenu#ClientsMenu(CoreBanking)}
  */
  @Test
  public void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new ClientsMenu(readFromDBResult)).coreBanking, readFromDBResult);
  }
}

