package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.Test;

public class AccountsMenuDiffblueTest {
  /**
  * Method under test: {@link AccountsMenu#AccountsMenu(CoreBanking)}
  */
  @Test
  public void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new AccountsMenu(readFromDBResult)).coreBanking, readFromDBResult);
  }
}

