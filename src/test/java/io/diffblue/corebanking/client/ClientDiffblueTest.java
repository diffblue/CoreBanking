package io.diffblue.corebanking.client;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.AccountException;
import org.junit.Test;

public class ClientDiffblueTest {
  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>{@link Client#Client(String)}
  *   <li>{@link Client#getClientName()}
  * </ul>
  */
  @Test
  public void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Dr Jane Doe", (new Client("Dr Jane Doe")).getClientName());
  }

  /**
   * Method under test: {@link Client#addAccount(Account)}
   */
  @Test
  public void testAddAccount() {
    // Arrange
    Client client = new Client("Dr Jane Doe");

    // Act
    client.addAccount(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals(1, client.getAccounts().size());
  }

  /**
   * Method under test: {@link Client#toString()}
   */
  @Test
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("Client name: Dr Jane Doe\n", (new Client("Dr Jane Doe")).toString());
  }

  /**
   * Method under test: {@link Client#toString()}
   */
  @Test
  public void testToString2() {
    // Arrange
    Client client = new Client("Dr Jane Doe");
    client.addAccount(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act and Assert
    assertEquals("Client name: Dr Jane Doe\n"
        + "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
        + " state: OPEN\t |\n" + "Account statement empty.\n", client.toString());
  }

  /**
   * Method under test: {@link Client#toString()}
   */
  @Test
  public void testToString3() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    account.addTransaction(null);

    Client client = new Client("Dr Jane Doe");
    client.addAccount(account);

    // Act and Assert
    assertEquals("Client name: Dr Jane Doe\n"
        + "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
        + " state: OPEN\t |\n" + "null\n" + "\n", client.toString());
  }
}

