package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.Test;

public class CoreBankingDataManagementMenuDiffblueTest {
  /**
  * Method under test: {@link CoreBankingDataManagementMenu#CoreBankingDataManagementMenu(CoreBanking)}
  */
  @Test
  public void testConstructor() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act and Assert
    assertSame((new CoreBankingDataManagementMenu(readFromDBResult)).coreBanking, readFromDBResult);
  }

  /**
   * Method under test: {@link CoreBankingDataManagementMenu#executeMenuOption(int)}
   */
  @Test
  public void testExecuteMenuOption() throws TransactionException {
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
    Client getResult1 = clients.get(1);
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals(2, getResult1.getAccounts().size());
    assertEquals(1, getResult.getAccounts().size());
    Client getResult2 = clients.get(0);
    assertEquals(3, getResult2.getAccounts().size());
    assertEquals("John Field", getResult2.getClientName());
  }
}

