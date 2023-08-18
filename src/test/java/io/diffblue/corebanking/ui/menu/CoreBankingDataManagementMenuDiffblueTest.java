package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CoreBankingDataManagementMenuDiffblueTest {
  /**
  * Method under test: {@link CoreBankingDataManagementMenu#CoreBankingDataManagementMenu(CoreBanking)}
  */
  @Test
  void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new CoreBankingDataManagementMenu(coreBanking)).coreBanking, coreBanking);
  }

  /**
   * Method under test: {@link CoreBankingDataManagementMenu#executeMenuOption(int)}
   */
  @Test
  void testExecuteMenuOption() throws TransactionException {
    // Arrange
    CoreBankingDataManagementMenu coreBankingDataManagementMenu = new CoreBankingDataManagementMenu(
        ReadFromDB.readFromDB());

    // Act
    coreBankingDataManagementMenu.executeMenuOption(2);

    // Assert
    CoreBanking coreBanking = coreBankingDataManagementMenu.coreBanking;
    assertEquals(6, coreBanking.getAccounts().size());
    List<Client> clients = coreBanking.getClients();
    assertEquals(3, clients.size());
    Client getResult = clients.get(2);
    assertEquals("Emily Simmons", getResult.getClientName());
    Client getResult2 = clients.get(1);
    assertEquals("Jane Robbins", getResult2.getClientName());
    assertEquals(2, getResult2.getAccounts().size());
    assertEquals(1, getResult.getAccounts().size());
    Client getResult3 = clients.get(0);
    assertEquals(3, getResult3.getAccounts().size());
    assertEquals("John Field", getResult3.getClientName());
  }
}

