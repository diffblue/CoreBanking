package io.diffblue.corebanking.datamanagement;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.Test;

public class ReadFromDBDiffblueTest {
  @Test
  public void testReadFromDB() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
    List<Client> clients = coreBanking.getClients();
    Client getResult = clients.get(0);
    Client getResult1 = clients.get(1);
    String actualClientName = clients.get(2).getClientName();
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals("Emily Simmons", actualClientName);
    assertEquals("John Field", getResult.getClientName());
  }

  @Test
  public void testReadFromDB2() throws TransactionException {
    // Arrange, Act and Assert
    List<Client> clients = ReadFromDB.readFromDB().getClients();
    Client getResult = clients.get(0);
    Client getResult1 = clients.get(1);
    String actualClientName = clients.get(2).getClientName();
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals("Emily Simmons", actualClientName);
    assertEquals("John Field", getResult.getClientName());
  }
}

