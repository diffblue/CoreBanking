package io.diffblue.corebanking.datamanagement;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.Test;

public class ReadFromDBDiffblueTest {
  /**
  * Method under test: {@link ReadFromDB#readFromDB()}
  */
  @Test
  public void testReadFromDB() throws TransactionException {
    // Arrange and Act
    CoreBanking actualReadFromDBResult = ReadFromDB.readFromDB();

    // Assert
    assertEquals(6, actualReadFromDBResult.getAccounts().size());
    List<Client> clients = actualReadFromDBResult.getClients();
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

  /**
   * Method under test: {@link ReadFromDB#readFromDB(CoreBanking)}
   */
  @Test
  public void testReadFromDB2() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act
    ReadFromDB.readFromDB(readFromDBResult);

    // Assert
    assertEquals(6, readFromDBResult.getAccounts().size());
    List<Client> clients = readFromDBResult.getClients();
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

