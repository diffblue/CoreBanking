package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AccountsMenuDiffblueTest {
  /**
  * Method under test: {@link AccountsMenu#AccountsMenu(CoreBanking)}
  */
  @Test
  void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new AccountsMenu(coreBanking)).coreBanking, coreBanking);
  }

  /**
   * Method under test: {@link AccountsMenu#executeMenuOption(int)}
   */
  @Test
  void testExecuteMenuOption() {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.getAccounts()).thenReturn(new ArrayList<>());

    // Act
    (new AccountsMenu(coreBanking)).executeMenuOption(1);

    // Assert
    verify(coreBanking).getAccounts();
  }
}

