package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.Test;

public class CoreBankingDiffblueTest {
  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>default or parameterless constructor of {@link CoreBanking}
  *   <li>{@link CoreBanking#getAccounts()}
  *   <li>{@link CoreBanking#getClients()}
  * </ul>
  */
  @Test
  public void testConstructor() {
    // Arrange and Act
    CoreBanking actualCoreBanking = new CoreBanking();

    // Assert
    List<Account> expectedClients = actualCoreBanking.getAccounts();
    assertEquals(expectedClients, actualCoreBanking.getClients());
  }

  /**
   * Method under test: {@link CoreBanking#purgeCoreBanking()}
   */
  @Test
  public void testPurgeCoreBanking() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();

    // Act
    readFromDBResult.purgeCoreBanking();

    // Assert
    assertTrue(readFromDBResult.getAccounts().isEmpty());
    assertTrue(readFromDBResult.getClients().isEmpty());
  }

  /**
   * Method under test: {@link CoreBanking#openNewAccount(Client, long)}
   */
  @Test
  public void testOpenNewAccount() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Peter");

    // Act
    Account actualOpenNewAccountResult = readFromDBResult.openNewAccount(client, 10L);

    // Assert
    assertEquals("Current", actualOpenNewAccountResult.getAccountName());
    assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
    Client client1 = actualOpenNewAccountResult.getClient();
    assertSame(client, client1);
    assertEquals(Account.AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
    assertEquals(1, client1.getAccounts().size());
    assertTrue(actualOpenNewAccountResult.getAccountStatement().getTransactions().isEmpty());
    assertEquals(7, readFromDBResult.getAccounts().size());
  }

  /**
   * Method under test: {@link CoreBanking#registerNewClient(Client)}
   */
  @Test
  public void testRegisterNewClient() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Peter");

    // Act and Assert
    assertSame(client, readFromDBResult.registerNewClient(client));
    assertEquals(4, readFromDBResult.getClients().size());
  }
}

