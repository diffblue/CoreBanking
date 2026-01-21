package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CoreBankingDiffblueTest {
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
  void testConstructor() {
    // Arrange and Act
    CoreBanking actualCoreBanking = new CoreBanking();
    List<Account> actualAccounts = actualCoreBanking.getAccounts();
    List<Client> actualClients = actualCoreBanking.getClients();

    // Assert
    assertTrue(actualAccounts.isEmpty());
    assertTrue(actualClients.isEmpty());
  }

  /**
   * Method under test: {@link CoreBanking#purgeCoreBanking()}
   */
  @Test
  void testPurgeCoreBanking() throws TransactionException {
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
  void testOpenNewAccount() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualOpenNewAccountResult = readFromDBResult.openNewAccount(client, 10L);

    // Assert
    assertEquals("Current", actualOpenNewAccountResult.getAccountName());
    assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
    Client client2 = actualOpenNewAccountResult.getClient();
    assertSame(client, client2);
    assertEquals(Account.AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
    assertEquals(1, client2.getAccounts().size());
    assertTrue(actualOpenNewAccountResult.getAccountStatement().getTransactions().isEmpty());
    assertEquals(7, readFromDBResult.getAccounts().size());
  }

  /**
   * Method under test: {@link CoreBanking#registerNewClient(Client)}
   */
  @Test
  void testRegisterNewClient() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Dr Jane Doe");

    // Act and Assert
    assertSame(client, readFromDBResult.registerNewClient(client));
    assertEquals(4, readFromDBResult.getClients().size());
  }
}

