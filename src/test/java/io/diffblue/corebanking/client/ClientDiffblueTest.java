package io.diffblue.corebanking.client;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.account.Account;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientDiffblueTest {
  @Test
  public void testClientConstructor() throws Exception {
    Client client = new Client("John Doe");
    Assertions.assertEquals("John Doe", client.getClientName());
  }

  @Test
  public void testGetClientName() throws Exception {
    Client client = new Client("John Doe");
    Assertions.assertEquals("John Doe", client.getClientName());
  }

  @Test
  public void testAddAccount() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    client.addAccount(account);
    List<Account> accounts = client.getAccounts();
    assertTrue(accounts.contains(account), "The account should be added to the client's accounts");
  }

  @Test
  public void testGetAccounts() throws Exception {
    Client client = new Client("John Doe");
    Account account = new Account(1234, client, 1000);
    client.addAccount(account);
    List<Account> accounts = client.getAccounts();
    Assertions.assertEquals(1, accounts.size());
    Assertions.assertEquals(account, accounts.get(0));
  }
}
