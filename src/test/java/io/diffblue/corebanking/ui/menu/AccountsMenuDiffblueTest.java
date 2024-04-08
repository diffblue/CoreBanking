package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AccountsMenuDiffblueTest {
  @Test
  public void testAccountsMenuConstructor() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    AccountsMenu accountsMenu = new AccountsMenu(coreBanking);
    assertNotNull(accountsMenu);
  }

  @Test
  public void testExecuteMenuOption() throws Exception {
    CoreBanking coreBanking = Mockito.mock(CoreBanking.class);
    AccountsMenu accountsMenu = new AccountsMenu(coreBanking);
    int menuOpt = 1;
    accountsMenu.executeMenuOption(menuOpt);
    verify(coreBanking, times(1)).getAccounts();
  }
}
