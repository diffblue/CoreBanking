package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoreBankingDiffblueTest {
  private CoreBanking coreBanking;
  @Test
  void purgeCoreBanking() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    Client client = new Client("Test Client");
    coreBanking.registerNewClient(client);
    coreBanking.openNewAccount(client, 1000);
    assertFalse(coreBanking.getClients().isEmpty());
    assertFalse(coreBanking.getAccounts().isEmpty());
    coreBanking.purgeCoreBanking();
    assertTrue(coreBanking.getClients().isEmpty());
    assertTrue(coreBanking.getAccounts().isEmpty());
  }

  @Test
  void openNewAccountTest() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    Client client = new Client("Test Client");
    long initialAmount = 1000;
    Account newAccount = coreBanking.openNewAccount(client, initialAmount);
    assertNotNull(newAccount, "New account should not be null");
    assertEquals(client, newAccount.getClient(), "Client of the new account should be the same as the input client");
    assertEquals(initialAmount, newAccount.getCurrentBalance(),
        "Initial balance of the new account should be the same as the input amount");
  }

  @Test
  void registerNewClient() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    Client client = new Client("Test Client");
    Client registeredClient = coreBanking.registerNewClient(client);
    assertNotNull(registeredClient, "Registered client should not be null");
    assertEquals(client.getClientName(), registeredClient.getClientName(), "Client names should match");
    assertTrue(coreBanking.getClients().contains(registeredClient),
        "Registered client should be in the list of clients");
  }

  @BeforeEach
  void setUp() throws Exception {
    coreBanking = new CoreBanking();
  }

  @Test
  void testGetAccounts() throws Exception {
    List<Account> accounts = coreBanking.getAccounts();
    assertNotNull(accounts);
    assertTrue(accounts.isEmpty());
  }

  @Test
  void testGetClients() throws Exception {
    List<Client> clients = coreBanking.getClients();
    assertNotNull(clients);
    assertTrue(clients.isEmpty());
  }

  @Test
  void testConstructor() throws Exception {
    assertNotNull(coreBanking);
  }
}
