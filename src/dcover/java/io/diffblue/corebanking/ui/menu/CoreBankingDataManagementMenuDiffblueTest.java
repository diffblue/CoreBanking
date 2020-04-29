package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.Test;

public class CoreBankingDataManagementMenuDiffblueTest {

  @Test
  public void testExecuteMenuOption() {
    // Arrange
    CoreBankingDataManagementMenu coreBankingDataManagementMenu = new CoreBankingDataManagementMenu(new CoreBanking());

    // Act
    coreBankingDataManagementMenu.executeMenuOption(2);

    // Assert
    List<Client> clients = coreBankingDataManagementMenu.coreBanking.getClients();
    Client getResult = clients.get(0);
    Client getResult1 = clients.get(1);
    String actualClientName = clients.get(2).getClientName();
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals("Emily Simmons", actualClientName);
    assertEquals("John Field", getResult.getClientName());
  }
}

