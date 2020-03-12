package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CoreBankingDataManagementMenuDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame(coreBanking, (new CoreBankingDataManagementMenu(coreBanking)).coreBanking);
  }

  @Test
  public void executeMenuOptionTest() {
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

