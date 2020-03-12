package io.diffblue.corebanking.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.diffblue.corebanking.account.Account;
import org.junit.jupiter.api.Test;

public class ClientDiffblueTest {
  @Test
  public void addAccountTest() {
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

  @Test
  public void constructorTest() {
    // Arrange and Act
    Client actualClient = new Client("");

    // Assert
    assertEquals("", actualClient.getClientName());
    assertEquals("Client name: \n", actualClient.toString());
  }

  @Test
  public void toStringTest() {
    // Arrange, Act and Assert
    assertEquals("Client name: \n", (new Client("")).toString());
  }
}

