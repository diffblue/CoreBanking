package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import org.junit.Test;

public class ClientsMenuDiffblueTest {

  @Test
  public void testExecuteMenuOption() {
    // Arrange
    ClientsMenu clientsMenu = new ClientsMenu(new CoreBanking());

    // Act
    clientsMenu.executeMenuOption(2);

    // Assert
    CoreBanking coreBanking = clientsMenu.coreBanking;
    Client getResult = coreBanking.getClients().get(0);
    assertNull(getResult.getClientName());
  }
}

