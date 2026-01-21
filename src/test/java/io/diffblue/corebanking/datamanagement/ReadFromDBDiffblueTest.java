package io.diffblue.corebanking.datamanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReadFromDBDiffblueTest {
  /**
  * Method under test: {@link ReadFromDB#readFromDB()}
  */
  @Test
  void testReadFromDB() throws TransactionException {
    // Arrange and Act
    CoreBanking actualReadFromDBResult = ReadFromDB.readFromDB();

    // Assert
    assertEquals(6, actualReadFromDBResult.getAccounts().size());
    List<Client> clients = actualReadFromDBResult.getClients();
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

  /**
   * Method under test: {@link ReadFromDB#readFromDB(CoreBanking)}
   */
  @Test
  void testReadFromDB2() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = ReadFromDB.readFromDB();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
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

