package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.Account.AccountState;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CoreBankingDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link CoreBanking}
   *   <li>{@link CoreBanking#getAccounts()}
   *   <li>{@link CoreBanking#getClients()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CoreBanking.<init>()",
    "List CoreBanking.getAccounts()",
    "List CoreBanking.getClients()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    CoreBanking actualCoreBanking = new CoreBanking();
    List<Account> actualAccounts = actualCoreBanking.getAccounts();
    List<Client> actualClients = actualCoreBanking.getClients();

    // Assert
    assertTrue(actualAccounts.isEmpty());
    assertTrue(actualClients.isEmpty());
  }

  /**
   * Test {@link CoreBanking#purgeCoreBanking()}.
   *
   * <p>Method under test: {@link CoreBanking#purgeCoreBanking()}
   */
  @Test
  @DisplayName("Test purgeCoreBanking()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBanking.purgeCoreBanking()"})
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
   * Test {@link CoreBanking#openNewAccount(Client, long)}.
   *
   * <ul>
   *   <li>Then return AccountName is {@code Current}.
   * </ul>
   *
   * <p>Method under test: {@link CoreBanking#openNewAccount(Client, long)}
   */
  @Test
  @DisplayName("Test openNewAccount(Client, long); then return AccountName is 'Current'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Account CoreBanking.openNewAccount(Client, long)"})
  void testOpenNewAccount_thenReturnAccountNameIsCurrent() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualOpenNewAccountResult = readFromDBResult.openNewAccount(client, 10L);

    // Assert
    assertEquals("Current", actualOpenNewAccountResult.getAccountName());
    assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
    assertEquals(7, readFromDBResult.getAccounts().size());
    assertEquals(AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
    assertTrue(actualOpenNewAccountResult.getAccountStatement().getTransactions().isEmpty());
    assertSame(client, actualOpenNewAccountResult.getClient());
  }

  /**
   * Test {@link CoreBanking#registerNewClient(Client)}.
   *
   * <p>Method under test: {@link CoreBanking#registerNewClient(Client)}
   */
  @Test
  @DisplayName("Test registerNewClient(Client)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Client CoreBanking.registerNewClient(Client)"})
  void testRegisterNewClient() throws TransactionException {
    // Arrange
    CoreBanking readFromDBResult = ReadFromDB.readFromDB();
    Client client = new Client("Dr Jane Doe");

    // Act
    Client actualRegisterNewClientResult = readFromDBResult.registerNewClient(client);

    // Assert
    List<Client> clients = readFromDBResult.getClients();
    assertEquals(4, clients.size());
    assertSame(client, actualRegisterNewClientResult);
    assertSame(client, clients.get(3));
  }
}
