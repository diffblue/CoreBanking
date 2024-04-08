package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ClientsMenuDiffblueTest {
  @Test
  void testClientsMenuConstructor() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    ClientsMenu clientsMenu = new ClientsMenu(coreBanking);
    assertNotNull(clientsMenu);
  }

  @Test
  public void testExecuteMenuOption() throws Exception {
    CoreBanking coreBanking = Mockito.mock(CoreBanking.class);
    ClientsMenu clientsMenu = new ClientsMenu(coreBanking);
    clientsMenu.executeMenuOption(1);
    verify(coreBanking, times(1)).getClients();
  }
}
