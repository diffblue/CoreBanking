package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

  /**
   * Method under test: {@link AccountsMenu#AccountsMenu(CoreBanking)}
   */
  @Test
  void testNewAccountsMenu() throws TransactionException {
    // Arrange, Act and Assert
    assertEquals(6, (new AccountsMenu(ReadFromDB.readFromDB())).coreBanking.getAccounts().size());
  }
}
