package io.diffblue.corebanking.client;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import org.junit.Test;

public class ClientDiffblueTest {

  @Test
  public void testAddAccount() {
    // Arrange
    Client client = new Client("");
    Account account = new Account(1234567890L, client, 10L);

    // Act
    client.addAccount(account);

    // Assert
    assertEquals(
        "Client name: \n" + "Account: | Acc #: 1234567890\t | Acc name: Current"
            + "\t | Acc holder: \t | Acc balance: 10\t | Acc state:" + " OPEN\t |\n" + "Account statement empty.\n",
        account.getClient().toString());
  }
}

